/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phoniex;


import com.wcohen.ss.Jaccard;
import com.wcohen.ss.JaroWinkler;
import com.wcohen.ss.api.StringDistance;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import org.jaxen.JaxenException;
import org.jaxen.jdom.JDOMXPath;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import phoniex.Core;
import phoniex.IProcesser;
import phoniex.Xliff.AltTrans;
import phoniex.Xliff.MetaData;
import phoniex.Xliff.XliffElement;

/**
 *
 * @author Admin
 */




public class Processer implements IProcesser {


Document repos=null;
Document source=null;
Document target=null;
JProgressBar progress=null;



    public Processer(Document repos, Document source) {
        this.repos=repos;
        this.source=source;
        target=new Document((Element)source.getRootElement().clone());
//        try{
//            target= Core.targetFilter(target);
//        }
//        catch(Exception e){}
    }
    @Override
    public Document getRepos() {
        return repos;
    }


    @Override
    public void setRepos(Document repos) {
        this.repos = repos;
    }

    @Override
    public Document getSource() {
        return source;
    }

    @Override
    public void setSource(Document source) {
        this.source = source;
    }
    @Override
    public void setProgress(JProgressBar progress) {
        this.progress = progress;
    }
    @Override
    public Document process(){
        try{
        List<Element> sourceList = Core.filter("//xliff:trans-unit", target);
        for(int i = 0; i< sourceList.size();i++){
            Element current =sourceList.get(i);
            String sStr=current.getChildText("source",XliffElement.xliff);
           // sStr=sStr.replaceAll("\\'","''");
            String path="//xliff:trans-unit[xliff:source=\""+sStr+"\"]//xliff:target";
            JDOMXPath match = new JDOMXPath(path);
            match.addNamespace("xliff", "urn:oasis:names:tc:xliff:document:1.2");
            match.addNamespace( "lmc", "http://www.localisation.ie/lmc");
            Element matchRepos = (Element)match.selectSingleNode(repos);
            if (matchRepos!=null){
                sourceList.get(i).addContent(new AltTrans(matchRepos.getChild("target",XliffElement.xliff).getTextNormalize(),
                        matchRepos.getChild("target",XliffElement.xliff).getAttributeValue("lang",Namespace.XML_NAMESPACE),
                        matchRepos.getChild("source",XliffElement.xliff).getTextNormalize(),
                        matchRepos.getChild("source",XliffElement.xliff).getAttributeValue("lang",Namespace.XML_NAMESPACE)
                 ));

            }

        }


        }
         catch (JaxenException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return target;

    }

    @Override
    public Document process(double percent){
        // creat list of all trans units and do manual search. possible binary sort
        List<Element> sourceList = Core.filter("//xliff:trans-unit", target);
        if(sourceList.size()==0) sourceList = Core.filter("//trans-unit", target);
        List<Element> reposList = Core.filter("//xliff:trans-unit", repos);
//        QuickHeapSort sorter = new QuickHeapSort();
//        sorter.sort(reposList);
        StringDistance jaroWinkler= new JaroWinkler();
        //StringDistance levenstein= new Level2Levenstein();
        StringDistance jaccard = new Jaccard();
        int count=0;
        double totalmatch=0;



        for(int i = 0; i< sourceList.size();i++){
            if(progress!=null){
                progress.setMaximum(sourceList.size()-1);
                progress.setValue(i);
            }
            Element current =sourceList.get(i);
            String sStr=current.getChildText("source",XliffElement.xliff);
            if(sStr==null)sStr=current.getChildText("source");
            double score=Double.NEGATIVE_INFINITY;

            Element matchRepos;
            int x=-1;
            int best=-1;
            double bestScore=score;
            String rStr="";
            double jaccardScore=score;
//            double smithWatermanScore=score;
            while(x<reposList.size()-1 && score<0.999){
                rStr=reposList.get(++x).getChildText("source",XliffElement.xliff);

                jaccardScore=jaccard.score(sStr, rStr);

//                if(sStr.length()<test.length())smithWatermanScore=smithWaterman.score(sStr, test)/test.length();
//                else smithWatermanScore=smithWaterman.score(sStr, test)/sStr.length();
                double k = 5;
                score=(k*jaroWinkler.score(sStr,rStr)+jaccardScore)/(k+1);

                if(bestScore<score){
                           best=x;
                           bestScore = score;
                    }
            }
            double length = 32;
            if(best>=0&&bestScore>percent) matchRepos=reposList.get(best);
            else if(best>=0&&bestScore>(percent-(Math.pow((1-percent)*percent,2))*(length/sStr.length()))&&sStr.length()<32&&percent!=100)matchRepos=reposList.get(best);
            else matchRepos=null;
            if (matchRepos!=null){
                AltTrans alt = new AltTrans(matchRepos.getChild("target",XliffElement.xliff).getTextNormalize(),
                        attCheck("lang",Namespace.XML_NAMESPACE, matchRepos.getChild("target",XliffElement.xliff)),
                        matchRepos.getChild("source",XliffElement.xliff).getTextNormalize(),
                        attCheck("lang",Namespace.XML_NAMESPACE, matchRepos.getChild("source",XliffElement.xliff)),
                        Math.round(bestScore*100));
                Element file = matchRepos.getParentElement().getParentElement();
                //alt.setAttribute("tool-id",attCheck("tool-id", file));
                alt.setAttribute("datatype",attCheck("datatype",file));
//                MetaData meta = new MetaData();
//                meta.extract(matchRepos);
//                Note metaNote = new Note(meta);
//                alt.addContent(metaNote);
                MetaData.extractPI(matchRepos, alt);
                sourceList.get(i).addContent(alt);
                count++;
                totalmatch+=bestScore;
            }

        }

        return target;

    }

    private String attCheck(String s, Element e){
        if(e==null)return "";
        Attribute at = e.getAttribute(s);
        if(at==null)return"";
        else return at.getValue().trim();
     }
     private String attCheck(String s,Namespace n, Element e){
        if(e==null)return "";
        Attribute at = e.getAttribute(s,n);
        if(at==null)return"";
        else return at.getValue().trim();
     }

}
