/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phoniex.Lmc;


import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import phoniex.Core;
import phoniex.Xliff.XliffElement;


/**
 *
 * @author Admin
 */
public class LmcBuilder implements ILmcBuilder {

    List<Document> docs= new ArrayList<Document>();
    boolean format =false;

    public boolean isFormat() {
        return format;
    }

    public void setFormat(boolean format) {
        this.format = format;
    }

    public LmcBuilder(Document doc) {
       docs.add((Document)doc.clone());
    }

    public LmcBuilder(List<Document> docs) {
        for(Document d:docs){
        this.docs.add((Document)d.clone());
        }
    }

     public LmcBuilder(Document doc, boolean format) {
         this(doc);
         this.format=format;
    }

    public LmcBuilder(List<Document> docs, boolean format) {
        this(docs);
        this.format=format;
    }
    @Override
    public Document build(){
         if(docs.size()>0&&format){
            for(Document d:docs)d=reformat(d);
        }
        Lmc lmc = new Lmc();
        for(Document d :docs){
        List<Element> all =Core.filter("//*", d);
        for(Element e:all)e.setNamespace(XliffElement.xliff);
        }
        LmcBody body =new LmcBody();
        for(Document d :docs) body.addDoc(d);
        lmc.addContent(body);

        return new Document((Element)lmc.detach());
    }
    @Override
    public Document build(Document doc){
        docs.clear();
        docs.add((Document)doc.clone());
        return build();
    }
    @Override
    public Document build(List<Document> docs){
        this.docs.clear();
            for(Document d:docs) this.docs.add((Document)d.clone());
        return build();
    }
    public Document build(boolean format){
        this.format=format;
        return build();
    }
    
    @Override
    public Document reformat(Document doc){
        Document clone= (Document) doc.clone();
        Element bestTarget = new Element("alt-trans").setAttribute("match-quality","0");
        List<Element> trans =Core.filter("//xliff:trans-unit", clone);
        for(Element e:trans){
            bestTarget = new Element("alt-trans").setAttribute("match-quality","0");
            if((e.getChildText("target", XliffElement.xliff).equals("")||e.getChild("target",XliffElement.xliff)==null||e.getChildText("target",XliffElement.xliff)==null)&&e.getChild("alt-trans")!=null){
                e.removeChildren("target",XliffElement.xliff);
                List<Element> alts =e.getChildren("alt-trans");
                for(Element t:alts){
                    if(Double.parseDouble(t.getAttributeValue("match-quality"))>Double.parseDouble(bestTarget.getAttributeValue("match-quality")))bestTarget=t ;
                }
                e.addContent(bestTarget.getChild("target").detach());
                e.removeChildren("alt-trans");
            }
        }

        return clone;
    }

}
