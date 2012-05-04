/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phoniex.IO;

import java.util.List;
import javax.swing.JOptionPane;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import phoniex.Core;

import org.jaxen.jdom.JDOMXPath;
import org.jdom.ProcessingInstruction;

import phoniex.Xliff.MetaData;
import phoniex.Xliff.Note;
import phoniex.Xliff.XliffElement;

/**
 *
 * @author Admin
 */
public class TmxDocExporter {
    Document doc= null;
    Document result = null;
    private TmxDocExporter() {
        doc=null;
        result=null;
    }
    public TmxDocExporter(Document doc){
        this();
        this.doc=doc;
    }
    public Document export(){
        Element root=new Element("tmx");
        root.setAttribute("version","1.4");
        result=new Document(root);
        Element header = new Element("header");
        header.setAttribute("creationtool","Xliff Phoenix");
        header.setAttribute("creationtoolversion","1.0");
        Attribute dataType = doc.getRootElement().getChild("file",XliffElement.xliff).getAttribute("datatype");
        header.setAttribute("datatype",dataType.getValue());
        header.setAttribute("segtype","sentence");
        header.setAttribute("adminlang","en");
        Attribute sourceLanguage = doc.getRootElement().getChild("file",XliffElement.xliff).getAttribute("source-language");
        header.setAttribute("srclang",sourceLanguage.getValue());
        header.setAttribute("o-tmf","Xliff");
        root.addContent(header);
        Element body = new Element("body");
        List<Element> altTrans = Core.filter("//alt-trans", doc);
        for(Element x:altTrans){
            body.addContent(convertAlt((Element)x.clone()));
        }
        root.addContent(body);
        return result;
    }
    public Document export(Document doc){
        this.doc=doc;
        return export();
    }
    public Element convertAlt(Element alt){
        Element tu = new Element("tu");
        try{
        Element match = new Element("prop");
        match.setAttribute("type","match-quality");
        match.addContent(alt.getAttributeValue("match-quality"));
        tu.addContent(match);
        Element meta = new Element("prop");
        meta.setAttribute("type","metadata");
        Element metaTemp = alt.getChild("note");
        if (metaTemp==null) {
            MetaData convertedMeta = new MetaData();
            try{
            JDOMXPath path = new JDOMXPath(".//processing-instruction()");
            path.addNamespace("xliff", "urn:oasis:names:tc:xliff:document:1.2");
            path.addNamespace( "lmc", "http://www.localisation.ie/lmc");
            path.addNamespace( "", "http://www.lisa.org/tmx14");
            List<ProcessingInstruction> pi = (List<ProcessingInstruction>)path.selectNodes(alt);
            for(ProcessingInstruction p :pi){
               convertedMeta.addData(p);
            }
            metaTemp = new Note(convertedMeta);
            }
            catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }
        if(metaTemp!=null&&metaTemp.getContent(0)!=null)meta.addContent(metaTemp.getContent(0).detach());
        tu.addContent(meta);
        Element tuvS = new Element("tuv");
        tuvS.setAttribute(alt.getChild("source").getAttribute("lang",Namespace.XML_NAMESPACE).detach());
        tuvS.addContent(new Element("seg").addContent(alt.getChild("source").getText()));
        tu.addContent(tuvS);
        Element tuvT = new Element("tuv");
        tuvT.setAttribute(alt.getChild("target").getAttribute("lang",Namespace.XML_NAMESPACE).detach());
        tuvT.addContent(new Element("seg").addContent(alt.getChild("target").getText()));
        tu.addContent(tuvT);
        }
        catch(Exception e){
        JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return tu;
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
