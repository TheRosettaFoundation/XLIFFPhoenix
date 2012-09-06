/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lmc_builder.phoniex.Xliff;

/**
 *
 * @author Admin
 */
public class Tool extends XliffElement{

    public Tool(String id, String name) {
        super("tool");
        setToolId(id);
        setToolName(name);
    }
    public void setToolId(String s){
         setAttribute("tool-id", s);
    }
    public void setToolName(String s){
         setAttribute("tool-name", s);
    }
    public void setToolVersion(String s){
         setAttribute("tool-version", s);
    }
    public void setToolCompany(String s){
         setAttribute("tool-company", s);
    }
    public String getToolId(){
        return getAttributeValue("tool-id");
    }
    public String getToolName(){
        return getAttributeValue("tool-name");
    }
    public String getToolVersion(){
        return getAttributeValue("tool-version");
    }
    public String getToolCompany(){
        return getAttributeValue("tool-company");
    }

}
