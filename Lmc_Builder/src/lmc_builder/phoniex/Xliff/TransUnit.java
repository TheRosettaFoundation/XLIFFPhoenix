/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lmc_builder.phoniex.Xliff;

/**
 *
 * @author Admin
 */
public class TransUnit extends XliffElement{

    public TransUnit() {
        super("trans-unit");
    }

    public TransUnit(Source s, Target t) {
        this();
        addContent(s);
        addContent(t);
    }
    public void setId(String id){
        setAttribute("id",id);
    }

}