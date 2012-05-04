/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phoniex.Xliff;

import lmc_builder.phoniex.Xliff.Tool;
import lmc_builder.phoniex.Xliff.XliffElement;

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
