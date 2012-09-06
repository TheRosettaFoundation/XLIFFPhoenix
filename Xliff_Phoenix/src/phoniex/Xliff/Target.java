/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phoniex.Xliff;

import lmc_builder.phoniex.Xliff.XliffElement;

/**
 *
 * @author sean
 */
public class Target extends XliffElement{

    public Target(){
        super("target");
    }

    public Target(String s){
        this();
        addText(s);
    }


     public Target(String s,String lang){
        this(s);
        setLanguage(lang);
    }

}