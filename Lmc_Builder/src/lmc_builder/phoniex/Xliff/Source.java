/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lmc_builder.phoniex.Xliff;

import org.jdom.Element;

/**
 *
 * @author sean
 */
public class Source extends XliffElement{

    public Source(String s){
        super("source");
        addText(s);
    }
    
    public Source(String s,String lang){
        super("source");
        addText(s);
        setLanguage(lang);
    }
}
