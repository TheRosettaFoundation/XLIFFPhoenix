/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phoniex.Lmc;


import lmc_builder.phoniex.Lmc.LmcHeader;
import org.jdom.Element;
import org.jdom.Namespace;

import lmc_builder.phoniex.Xliff.XliffElement;

/**
 *
 * @author Admin
 */
public class Lmc extends Element {
public final static Namespace lmc = Namespace.getNamespace("lmc", "http://www.localisation.ie/lmc");
public final static Namespace xsi = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");

    public Lmc(){
        super("lmc");
        setNamespace(lmc);
        addNamespaceDeclaration(xsi);
        setAttribute("schemaLocation","http://www.localisation.ie/lmc/lmc.xsd",xsi);
        addContent(new LmcHeader());
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
        removeChild(s,lmc);
        addContent(x);
    }
   



}