/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phoniex.IO;

import java.io.File;
import javax.swing.JOptionPane;
import org.jdom.Document;
import org.jdom.transform.XSLTransformException;
import org.jdom.transform.XSLTransformer;
import phoniex.PhoenixApp;

/**
 *
 * @author Admin
 */
public class HtmlTransfromer extends TransformerBase {
    static String htmlStyleLoc =  PhoenixApp.class.getResource("resources/HtmlStyle.xsl").getFile();
    static File styleSheet = null;
    public HtmlTransfromer() {
        super();
        try{

        trans = new XSLTransformer(PhoenixApp.class.getResourceAsStream("resources/HtmlStyle.xsl"));
        }
        catch(XSLTransformException e){
        JOptionPane.showMessageDialog(null, e);
        }
    }

    public HtmlTransfromer(Document doc) {
        this();
        this.doc=doc;
    }

     public static File getHtmlStyleSheet() {
        if(styleSheet==null){
          htmlStyleLoc= htmlStyleLoc.replace("%20"," ");
          styleSheet=new File(htmlStyleLoc);
        }
        return styleSheet;
    }

}





