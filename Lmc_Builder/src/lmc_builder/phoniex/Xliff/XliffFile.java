/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lmc_builder.phoniex.Xliff;

/**
 *
 * @author Admin
 */
public class XliffFile extends XliffElement{

    private XliffFile(){
        super("file");
    }

    public XliffFile(String src, String datatype , String original){
        this();
        setSourceLanguage(src);
        setDatatype(datatype);
        setOriginal(original);
    }

    public void setOriginal(String s){
         setAttribute("original", s);
    }
    public void setSourceLanguage(String s){
         setAttribute("source-language", s);
    }
    public void setDatatype(String s){
         setAttribute("datatype", s);
    }
    public String getOriginal(){
        return getAttributeValue("original");
    }
    public String getSourceLanguage(){
        return getAttributeValue("source-language");
    }
    public String getDatatype(){
        return getAttributeValue("datatype");
    }

}
