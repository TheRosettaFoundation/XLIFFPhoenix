/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lmc_builder.phoniex.Xliff;

import org.jdom.Namespace;

/**
 *
 * @author Admin
 */
public class Xliff extends XliffElement{

    public Xliff(){
        super("xliff");
        setVersion("1.2");
        setNamespace(Namespace.getNamespace("urn:oasis:names:tc:xliff:document:1.2"));
    }

    public Xliff(String s){
        this();
        addText(s);
    }

    public final void setVersion(String s){
         setAttribute("version", s);
    }



}
