/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lmc_builder.phoniex.Xliff;

/**
 *
 * @author Admin
 */
public class XliffHeader extends XliffElement{

    public XliffHeader(){
        super("header");
    }
    public XliffHeader(Tool t){
        super("header");
        addContent(t);
    }


}
