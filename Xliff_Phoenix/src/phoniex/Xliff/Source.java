/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phoniex.Xliff;

import lmc_builder.phoniex.Xliff.XliffElement;
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
