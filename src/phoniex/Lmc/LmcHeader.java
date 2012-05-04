/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phoniex.Lmc;

import lmc_builder.phoniex.Lmc.Lmc;
import lmc_builder.phoniex.Xliff.XliffElement;

/**
 *
 * @author Admin
 */
public class LmcHeader extends XliffElement{


    public LmcHeader() {
        super("header");
        setAuthor(System.getProperty("user.name"));
        setNamespace(Lmc.lmc);
    }

    public LmcHeader(String aut) {
        this();
        setAuthor(aut);
    }

    @Override
    public void setLanguage(String lang) {}

    public void setAuthor(String s){
        setAttribute("author", s);
    }
    public void setStartDate(String s){
        setAttribute("started", s);
    }
    public void setLastModified(String s){
        setAttribute("last-mod", s);
    }
    public void setTotalFiles(String s){
        setAttribute("total-files", s);
    }
    public String getAuthor(String s){
        return getAttributeValue("autor");
    }
    public String getStartDate(String s){
        return getAttributeValue("started");
    }
    public String getLastModified(String s){
        return getAttributeValue("last-mod");
    }
    public String getTotalFiles(String s){
        return getAttributeValue("total-files");
    }


}
