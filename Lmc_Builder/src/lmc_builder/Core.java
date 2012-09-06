/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lmc_builder;

import lmc_builder.IO.Input;
import lmc_builder.phoniex.Lmc.ILmcBuilder;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import lmc_builder.IO.Output;
import lmc_builder.IO.TmxToXliff;
import org.jaxen.JaxenException;
import org.jaxen.jdom.JDOMXPath;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import lmc_builder.phoniex.Lmc.Lmc;
import lmc_builder.phoniex.Lmc.LmcBuilder;
import lmc_builder.phoniex.Xliff.XliffElement;


/**
 *
 * @author sean
 */
public class Core {
    List<Document> doclist =new ArrayList<Document>();
    Document lmcDoc = null;
    JFileChooser chooser= new JFileChooser();
    ILmcBuilder builder;
    ArrayList<String> filenames= new ArrayList<String>();
    static Image logo = Toolkit.getDefaultToolkit().getImage(Lmc_BuilderApp.class.getResource("resources/Xliff_Phoenix_Logo.png"));

    public Core() {
        
        chooser= new JFileChooser();
        builder= new LmcBuilder(doclist);
    }
    public void loadLmc(File file){
        lmcDoc=Input.docLoad(file.getPath());
    }
    public void append(){
        Document result = lmcDoc;
        Element body = result.getRootElement().getChild("body",Lmc.lmc);
        for(Document d:doclist){
            List<Element> all =Core.filter("//*", d);
            for(Element e:all)e.setNamespace(XliffElement.xliff);
            d=builder.reformat(d);
            body.addContent(d.detachRootElement());
        }
        result.getRootElement().getChild("header",Lmc.lmc).setAttribute("last-mod",new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(new Date()));
        result.getRootElement().getChild("header",Lmc.lmc).setAttribute("total-files",
                ""+(Integer.parseInt(result.getRootElement().getChild("header",Lmc.lmc).getAttributeValue("total-files"))
                +doclist.size()));
        lmcDoc=result;
    }
    public void append(boolean convertall,boolean omit,String sLang, String tLang){
            TmxToXliff ttx = new TmxToXliff();
            ttx.setOmitSameLanguageConvertion(omit);
            List temp = new ArrayList();
            for(Document d: doclist){
               if(convertall){
                   List<Document> temp2 = ttx.convertAll(d);
                   for(Document current:temp2)temp.add(current.clone());
               }
               else {
                   temp.add(ttx.convert(d, sLang, tLang));
               }
            }
            doclist=temp;
            append();
    }

    public  void ttxExporter(String sLang, String tLang){
        TmxToXliff ttx= new TmxToXliff();
        Document ttxDoc =ttx.convert((Document)doclist.get(0), sLang, tLang);
        chooser.showSaveDialog(null);
        Output.save(ttxDoc, chooser.getSelectedFile().getAbsolutePath());

    }

    public static Image getLogo() {
        return logo;
    }
    public void setAuthor(String author){
        builder.setAuthor(author);
    }

    public void build(boolean tmx){
        if(tmx){
            TmxToXliff ttx = new TmxToXliff();
            List temp = new ArrayList();
            for(Document d: doclist){
                List<Document> temp2 = ttx.convertAll(d);
                for(Document current:temp2)temp.add(current.clone());
            }
            doclist=temp;
        }
        lmcDoc=builder.build(doclist);
    }
     public void build(boolean tmx,boolean omit){
       build( tmx,true,omit,"","");
     }
     public void build(boolean tmx,boolean convertall,boolean omit,String sLang,String tLang){
        if(tmx){
            TmxToXliff ttx = new TmxToXliff();
            ttx.setOmitSameLanguageConvertion(omit);
            List temp = new ArrayList();
            for(Document d: doclist){
               if(convertall){
                   List<Document> temp2 = ttx.convertAll(d);
                   for(Document current:temp2)temp.add(current);
               }
               else {
                   temp.add(ttx.convert(d, sLang, tLang));
               }
            }
            doclist=temp;
        }
        lmcDoc=builder.build(doclist);
       
    }
    public void loadDocs(File[] files){
        List<Document> fileList= new ArrayList<Document>();
        for(File f:files){
                fileList.add(Input.docLoad(f.getAbsolutePath()));
            }
        
        doclist=fileList;
    }
    public void loadDocs(File file){
        List<Document> fileList= new ArrayList<Document>();
        SAXBuilder builder = new SAXBuilder();
        try{
            fileList.add(builder.build(file));
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null,e.getMessage()+"\npleas check file");
        }
        catch (JDOMException e) {
            JOptionPane.showMessageDialog(null,e.getMessage()+"\npleas check file");
        }
        doclist=fileList;
    }

   
    
    public JFileChooser getChooser() {
        return chooser;
    }

    

    public void setChoser(JFileChooser choser) {
        this.chooser = choser;
    }

   

   

//     public void process(){
//         this.process= new Processer(repos, input);
//         output=process.process();
//     }
    
    public static List filter(String s, Document doc) {
        try{
            JDOMXPath path = new JDOMXPath(s);
            path.addNamespace("xliff", "urn:oasis:names:tc:xliff:document:1.2");
            path.addNamespace( "lmc", "http://www.localisation.ie/lmc");
            path.addNamespace( "", "http://www.lisa.org/tmx14");
            List temp = path.selectNodes(doc);
            return temp;
        }
        catch (JaxenException e) {
            JOptionPane.showMessageDialog(null,e.getMessage()+"\npleas check file");
        }
        return null;
    }

  

    public static String propcheck(String defaultString, String localString){
        String result= defaultString;
        if(localString!=""&&localString!=null) result=localString;
        return result;
    }
 
}
