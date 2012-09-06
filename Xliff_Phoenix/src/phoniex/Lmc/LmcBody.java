/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phoniex.Lmc;

import lmc_builder.phoniex.Lmc.Lmc;
import org.jdom.Document;
import lmc_builder.phoniex.Xliff.XliffElement;

/**
 *
 * @author Admin
 */
public class LmcBody extends XliffElement {

    public LmcBody() {
        super("body");
        setNamespace(Lmc.lmc);
    }
    public LmcBody(Document doc) {
        this();
        addContent(doc.detachRootElement());
    }
    public void addDoc(Document doc){
        addContent(doc.detachRootElement());
    }

}
