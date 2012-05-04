/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phoniex.IO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import phoniex.Core;
import lmc_builder.phoniex.Xliff.Source;
import lmc_builder.phoniex.Xliff.Target;
import lmc_builder.phoniex.Xliff.Tool;
import lmc_builder.phoniex.Xliff.TransUnit;
import lmc_builder.phoniex.Xliff.XliffFile;
import lmc_builder.phoniex.Xliff.Xliff;
import lmc_builder.phoniex.Xliff.XliffElement;
import lmc_builder.phoniex.Xliff.XliffHeader;

/**
 *
 * @author Admin
 */
public class TmxToXliff {
    Document doc=null;
    boolean omitSameLanguageConvertion= true;

    public TmxToXliff(Document doc) {
        this.doc=(Document)doc.clone();
    }
    public List<Document> convertAll(){
        return convertAll(doc.getRootElement().getChild("header").getAttributeValue("srclang"));
    }
    public List<Document> convertAll(Document doc){
        this.doc=doc;
        return convertAll(doc.getRootElement().getChild("header").getAttributeValue("srclang"));
    }
    public List<Document> convertAll(String sourceLang){
        List<Document> results = new ArrayList<Document>();
        ArrayList<String> langs = new ArrayList<String>();
        List temp= Core.filter("//tuv//@xml:lang", doc);
        for(Object o:temp){
            if(!langs.contains(((Attribute)o).getValue()))langs.add(((Attribute)o).getValue());
        }
        for(String lang:langs){
            if(lang.equalsIgnoreCase(sourceLang))continue;
            results.add(convert(doc,sourceLang,lang));
        }
        return results;
    }
    public Document convert(Document doc, String targetLang){
        this.doc=(Document)doc.clone();
        return convert(doc.getRootElement().getChild("header").getAttributeValue("srclang"),targetLang);
    }

    public Document convert(Document doc,String sourceLang, String targetLang){
        this.doc=(Document)doc.clone();
       return convert(sourceLang,targetLang);
    }
    public final static Namespace tmx = Namespace.getNamespace("", "http://www.lisa.org/tmx14");
    public Document convert(String sourceLang, String targetLang){
        Element tmxHeader = doc.getRootElement().getChild("header");
        Element tmxBody  =  doc.getRootElement().getChild("body");
        Xliff root = new Xliff();
        Document result=new Document(root);
        XliffFile file = new XliffFile(sourceLang,tmxHeader.getAttributeValue("datatype"),"TMX");//read filename
        file.setAttribute("date", XliffElement.attCheck(" creationdate", tmxHeader));
        if(file.getAttributeValue("date")=="") file.setAttribute("date", XliffElement.attCheck(" changedate", tmxHeader));
        if(file.getAttributeValue("date")==""){
            Date now = new Date();
            SimpleDateFormat xliffDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            file.setAttribute("date", xliffDate.format(now));
        }
        file.setAttribute("target-language", targetLang);
        file.setAttribute("tool-id", tmxHeader.getAttributeValue("creationtool"));
        XliffHeader head =new XliffHeader();
        Tool tool = new Tool(tmxHeader.getAttributeValue("creationtool"), tmxHeader.getAttributeValue("creationtool"));
        tool.setToolVersion(tmxHeader.getAttributeValue("creationtoolversion"));
        head.addContent(tool);
        file.addContent(head);
        Element body= new Element("body");
        List<Element> tus = Core.filter( "//tu",doc);
        for(Element e:tus){
            Source source =null;
            Target target =null;
            List<Element> notes= new ArrayList<Element>();
            if(e.getChild("note")!=null)notes.add(e.getChild("note"));
            for(Object o:e.getChildren("tuv")){
                Element current = (Element)o;
                if(current.getAttributeValue("lang", Namespace.XML_NAMESPACE).equalsIgnoreCase(sourceLang)){
                    source = new Source(current.getChildText("seg"),sourceLang);
                    if(current.getChild("note")!=null)notes.add(current.getChild("note"));
                }
                if(current.getAttributeValue("lang", Namespace.XML_NAMESPACE).equalsIgnoreCase(targetLang)){
                    target = new Target(current.getChildText("seg"), targetLang);
                    if(current.getChild("note")!=null)notes.add(current.getChild("note"));
                }
            }
            TransUnit trans = new TransUnit(source,target);
            trans.addContent(notes);
            trans.setId(""+tus.indexOf(e));//must be unique
            body.addContent(trans);
        }
        file.addContent(body);
        root.addContent(file);

        return result;
    }

}
