/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phoniex;

import phoniex.GUI.Jobs;
import phoniex.IO.Input;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import lmc_builder.Lmc_BuilderApp;
import org.jaxen.JaxenException;
import org.jaxen.jdom.JDOMXPath;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import lmc_builder.phoniex.Lmc.Lmc;
import lmc_builder.phoniex.Xliff.Target;
import lmc_builder.phoniex.Xliff.XliffElement;
import org.jaxen.SimpleFunctionContext;
import org.jaxen.XPathFunctionContext;

/**
 *
 * @author sean
 */
public class Core {
    Document repos = null;
    Document filteredRepos = repos;
    Document input = null;
    Document output = null;
    Document stats = null;
    Document jobListDoc=null;
    JFileChooser choser= new JFileChooser();
    Processer process;
    static Image logo = Toolkit.getDefaultToolkit().getImage(PhoenixApp.class.getResource("resources/Xliff_Phoenix_Logo.png"));
    JFrame joblist = null;
    String jobid="";
    String server="http://10.100.13.155/locConnect2.0/";

    public Document getStats() {
        return stats;
    }
    

    public Document getJobListDoc() {
        return jobListDoc;
    }
    public void retriveSorceXliff(){
        input= removeContentTag(Input.serverDocLoad(server,jobid));
    }
    public void retriveLmc(){
        repos=filteredRepos= removeContentTag(Input.getLMC(server,jobid));
    }

    public void setJobListDoc(Document jobListDoc) {
        this.jobListDoc = jobListDoc;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Core() {
        repos = null;
        filteredRepos = repos;
        input = null;
        output = null;
        choser= new JFileChooser();
        logo = Toolkit.getDefaultToolkit().getImage(PhoenixApp.class.getResource("resources/Xliff_Phoenix_Logo.png"));
    }

    public void lmcBuilder(){
       Thread builderThread = new Thread(new Lmc_BuilderApp());
       builderThread.run();
    }

    public static Image getLogo() {
        return logo;
    }

    public Document getFilteredRepos() {
        return filteredRepos;
    }

    public void displayJobs(){
        joblist=new Jobs();
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public JFrame getJoblist() {
        return joblist;
    }

    public void setJoblist(JFrame joblist) {
        this.joblist = joblist;
    }
   
    public void loadRepos(){
        FileFilter filter = new FileNameExtensionFilter("LMC files", "lmc");
        choser.addChoosableFileFilter(filter);
        choser.showOpenDialog(null);
        repos=Input.docLoad(choser.getSelectedFile().getPath());
        filteredRepos=repos;
    }
    public void loadSource(){
        FileFilter filter = new FileNameExtensionFilter("XLIFF files", "xlf");
        choser.addChoosableFileFilter(filter);
        choser.showOpenDialog(null);
        input=Input.docLoad(choser.getSelectedFile().getPath());
    }

    public Document getRepos() {
        return repos;
    }

    public Document getInput() {
        return input;
    }

    public JFileChooser getChoser() {
        return choser;
    }

    public Document getOutput() {
        return output;
    }

    public void setOutput(Document output) {
        this.output = output;
    }
     public void setFilteredRepos(Document output) {
        this.filteredRepos = output;
    }

    public void setInput(Document input) {
        this.input = input;
    }

    public void setChoser(JFileChooser choser) {
        this.choser = choser;
    }

    public void setProcess(Processer process) {
        this.process = process;
    }

    public void setRepos(Document repos) {
        this.repos = repos;
    }

     public void process(){
         this.process= new Processer(repos, input);
         output=process.process();
     }
    
    public static List filter(String s, Document doc) {
        try{
            JDOMXPath path = new JDOMXPath(s);

            path.addNamespace("xliff", "urn:oasis:names:tc:xliff:document:1.2");
            path.addNamespace( "lmc", "http://www.localisation.ie/lmc");
            path.addNamespace( "", "http://www.lisa.org/tmx14");
            path.addNamespace( "phoenix", "http://www.localisation.ie/phoenix/date");
            List temp = path.selectNodes(doc);
            return temp;
        }
        catch (JaxenException e) {
            JOptionPane.showMessageDialog(null,e.getMessage()+"\npleas check file");
        }
        return null;
    }

    public static Element select(String s,Document doc){
        Element lmc=null;
        
        List filtedDoc=filter(s,doc);
        lmc= new Lmc();
        for(int i=0; i<filtedDoc.size();i++){
            lmc.addContent((Element)((Element)filtedDoc.get(i)).clone());
        }
       
        
        return lmc;
    }

    public Element filter(String s,Document doc,JTextArea text){
        Element lmc=null;
        XMLOutputter out =new XMLOutputter(Format.getPrettyFormat());
        lmc= select(s,doc);
        text.setText(out.outputString(lmc));
        return lmc;
    }
    public static Document targetFilter(Document doc){
        List<Element> filterdDoc=filter("//xliff:trans-unit",doc);
        if(filterdDoc.size()==0)filterdDoc=filter("//trans-unit",doc);
        for(Element e:filterdDoc){
            e.removeChildren("target", XliffElement.xliff);
            e.removeChildren("target");
            e.addContent(new Target());
        }
        return doc;
    }
    public static Document altTransFilter(Document doc){
        List<Element> filterdDoc=filter("//xliff:alt-trans",doc);
        if(filterdDoc.size()==0)filterdDoc=filter("//alt-trans",doc);
        for(Element e:filterdDoc)e.detach();
        return doc;
    }

    public Document removeContentTag(Document doc){
        Element newRoot= doc.getRootElement().getChild("xliff",XliffElement.xliff);
        if (newRoot==null) newRoot =doc.getRootElement().getChild("lmc",Lmc.lmc);

        return new Document((Element)newRoot.detach());
    }

    public static String propcheck(String defaultString, String localString){
        String result= defaultString;
        if(localString!=""&&localString!=null) result=localString;
        return result;
    }
    public void generatStats(){
        StatBuilder builder = new StatBuilder(output);
        stats=builder.build();
    }
 
}
