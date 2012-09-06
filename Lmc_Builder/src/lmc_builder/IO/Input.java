/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lmc_builder.IO;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.jdom.Document;
import org.jdom.JDOMException;

import org.jdom.input.SAXBuilder;
/**
 *
 * @author sean
 */
public class Input {

    public static Document docLoad(String file){
        Document doc=null;
        SAXBuilder builder = new SAXBuilder("org.apache.xerces.parsers.SAXParser");
//        builder.setValidation(true);
//        builder.setFeature("http://xml.org/sax/features/validation",true);
//        builder.setFeature("http://apache.org/xml/features/validation/schema",true);
//        builder.setFeature("http://apache.org/xml/features/validation/schema-full-checking", true);
//        builder.setProperty(  "http://apache.org/xml/properties/schema/external-schemaLocation",
//        "xsi:schemaLocation='urn:oasis:names:tc:xliff:document:1.2 xliffcore-1.2-strict.xsd'");

    try {
     doc = builder.build(file);

    }
    // indicates a well-formedness error
    catch (JDOMException e) {
        JOptionPane.showMessageDialog(null, file + " is not well-formed.\n"+e.getMessage()+"\npleas check file");
    }
    catch (IOException e) {
        JOptionPane.showMessageDialog(null,file + "could not be checked.\n"+e.getMessage()+"\npleas check file");
    }
    return doc;

    }



}
