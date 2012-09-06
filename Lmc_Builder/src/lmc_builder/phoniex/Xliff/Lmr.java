/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lmc_builder.phoniex.Xliff;

import org.jdom.Element;
import org.jdom.Namespace;

/**
 *
 * @author Admin
 */
public class Lmr extends Element {
final static Namespace lmr = Namespace.getNamespace("lmr", "http://www.localisation.ie/lmr");
final static Namespace xsi = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");

    public Lmr(){
        super("lmr");
        setNamespace(lmr);
        addNamespaceDeclaration(xsi);
        setAttribute("schemaLocation","http://www.localisation.ie/lmr/lmr.xsd",xsi);
    }
   
    @Override
    public String getText(){
       return getTextNormalize();
    }
    public final void addText(String s){
        addContent(s);
    }
    public void setLanguage(String lang){
        setAttribute("lang", lang,Namespace.XML_NAMESPACE);
    }
    protected void replace(String s,XliffElement x){
        removeChild(s,lmr);
        addContent(x);
    }



}