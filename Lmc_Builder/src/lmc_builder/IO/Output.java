/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lmc_builder.IO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.Format.TextMode;
import org.jdom.output.XMLOutputter;
/**
 *
 * @author sean
 */
public class Output {
    
    public static void save(Document doc,String file){
        File outFile = new File(file);
        if(outFile.exists())
            if(1==JOptionPane.showOptionDialog(null, "Are you sure you wish to replace this file",
            "overwrite?", JOptionPane.CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, file)){
                JOptionPane.showMessageDialog(null, "save aborted");
                return;
            }
        try{
            save(doc,new FileOutputStream(outFile));
        }
         catch (IOException e) {
              JOptionPane.showMessageDialog(null, "Save unsucessful \n"+e.getMessage());
        }
      
    }

    public static void save(Document doc, OutputStream output ){

        try{
          XMLOutputter out = new XMLOutputter(org.jdom.output.Format.getPrettyFormat().setTextMode(TextMode.NORMALIZE).setIndent("    ").setOmitEncoding(true));

          String s = out.outputString(doc);
          s=s.replace("xmlns=\"\"", "");
          OutputStreamWriter osw=new OutputStreamWriter(output,"UTF8");
          osw.write(s+"<!-- \nThis file has been generated using LMC Builder,\na tool developed by Lucía Morado Vázquez & Seán Mooney in conjunction with the LRC, CNGL & University of Limerick\n-->\n");
          if (output.equals(System.out)) osw.flush();
          else osw.close();
        }
        catch (IOException e) {
           JOptionPane.showMessageDialog(null, "Save unsucessful \n"+e.getMessage());
        }
    }

    public static void display(Document doc, JTextArea tab){
        XMLOutputter out = new XMLOutputter(org.jdom.output.Format.getPrettyFormat().setTextMode(TextMode.NORMALIZE).setIndent("    "));
        tab.setText(out.outputString(doc).replace("xmlns=\"\"", ""));
    }
    public static void display(Document doc, JEditorPane tab){
        XMLOutputter out = new XMLOutputter(org.jdom.output.Format.getPrettyFormat().setTextMode(TextMode.NORMALIZE).setIndent("    "));
        tab.setText(indentString(doc));
    }
    public static Document indent(Document doc){
        String docString =indentString(doc);
        Document result = doc;
        try{
            result= new SAXBuilder().build(new StringReader(docString));
        }
        catch (IOException e) {
           JOptionPane.showMessageDialog(null, "Indent unsucessful \n"+e.getMessage());
        }
        catch(JDOMException e){
            JOptionPane.showMessageDialog(null, "Indent unsucessful \n"+e.getMessage());
        }
        return result;
    }

    public static String indentString(Document doc){
        XMLOutputter out = new XMLOutputter(org.jdom.output.Format.getPrettyFormat().setTextMode(TextMode.NORMALIZE).setIndent("    "));
        return out.outputString(doc)
                .replace("xmlns=\"\"", "")
                .replace("<xliff:trans-unit", "\n\t<xliff:trans-unit")
                .replace("<xliff:source", "\n\t\t\t<xliff:source")
                .replace("<xliff:target", "\n\t\t\t<xliff:target")
                .replace("></xliff:trans-unit>", ">\n\t</xliff:trans-unit>")
                .replace("<xliff:alt-trans", "\n\t\t<xliff:alt-trans")
                .replace("</xliff:alt-trans>", "\n\t\t<xliff:/alt-trans>")
                .replace("<xliff:note>", "\n\t\t\t\t<xliff:note>")
                .replace("<ph ", "\n\t\t\t\t<ph ")
                .replace("<trans-unit", "\n\t<trans-unit")
                .replace("<source", "\n\t\t\t<source")
                .replace("<target", "\n\t\t\t<target")
                .replace("></trans-unit>", ">\n\t</trans-unit>")
                .replace("<alt-trans", "\n\t\t<alt-trans")
                .replace("</alt-trans>", "\n\t\t</alt-trans>")
                .replace("<note>", "\n\t\t\t<note>");

    }
}


