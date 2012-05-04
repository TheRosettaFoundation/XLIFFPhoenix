/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phoniex.IO;

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
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

       save(doc,"",output);
    }

     public static void save(Document doc,String data, OutputStream output ){

        try{
           XMLOutputter out = new XMLOutputter(org.jdom.output.Format.getPrettyFormat().setTextMode(TextMode.NORMALIZE).setIndent("    ").setOmitEncoding(true));
          String s = out.outputString(doc);
          s=s.replace("xmlns=\"\"", "").replace("<?xml version=\"1.0\"?>", "").replace("<?encoding UTF-8?>", "");
          OutputStreamWriter osw=new OutputStreamWriter(output,"UTF8");
          osw.write(data+s+"<!-- \nThis file has been generated using XLIFF Phoenix,\na tool developed by Lucía Morado Vázquez & Seán Mooney in conjunction with the LRC, CNGL & University of Limerick\n-->\n");
          if (output.equals(System.out)) osw.flush();
          else osw.close();
        }
        catch (IOException e) {
           JOptionPane.showMessageDialog(null, "Save unsucessful \n"+e.getMessage());
        }
    }

    public static void display(Document doc, JTextArea tab){
        XMLOutputter out = new XMLOutputter(org.jdom.output.Format.getPrettyFormat().setTextMode(TextMode.NORMALIZE).setIndent("    ").setOmitEncoding(true));
        tab.setText(out.outputString(doc).replace("xmlns=\"\"", ""));
    }
    public static void display(Document doc, JEditorPane tab){
        XMLOutputter out = new XMLOutputter(org.jdom.output.Format.getPrettyFormat().setTextMode(TextMode.NORMALIZE).setIndent("    ").setOmitEncoding(true));
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
        XMLOutputter out = new XMLOutputter(org.jdom.output.Format.getPrettyFormat().setTextMode(TextMode.NORMALIZE).setIndent("    ").setOmitEncoding(true));
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
//         .replace("xmlns=\"\"", "")
//                .replace("</xliff:trans-unit><", "</xliff:trans-unit>\n<")
//                .replace("</xliff:source><", "</xliff:source>\n\t\t\t<")
//                .replace("</xliff:target><", "</xliff:target>\n\t\t\t<")
//                .replace("<xliff:source", "\n\t\t\t<xliff:source")
//                .replace("></xliff:trans-unit>", ">\n\t\t</xliff:trans-unit>")
//                .replace("<ph>", "\n\t<ph>")
//                .replace("</trans-unit><", "\n</trans-unit>\n\t<")
//                .replace("</source><", "</source>\n\t\t\t<")
//                .replace("</target><", "</target>\n\t\t\t<")
//                .replace("<source", "\n\t\t\t<source")
//                .replace("></trans-unit>", ">\n\t</trans-unit>")
//                .replace("><alt-trans", ">\n\t\t<alt-trans")
//                .replace("></alt-trans>", ">\n\t\t</alt-trans>")
//                .replace("<ph ", "\n\t\t\t\t<ph ");
       
    }


    public static void setStatus(String Server,String jobId,String message){
       try{
           String s =URLEncoder.encode(message, "UTF-8");
           URL server = new URL(Server+"set_status.php?id="+jobId+"&com=LMC&msg="+s);
           URLConnection in = server.openConnection();
           Document doc = new SAXBuilder("org.apache.xerces.parsers.SAXParser").build(in.getInputStream());
           in.getInputStream().close();
           if(!doc.getRootElement().getChildText("msg").equalsIgnoreCase("Status Updated"))
               JOptionPane.showMessageDialog(null, "Status update failed\n"+doc.getRootElement().getChildText("msg"));
       }
       catch (IOException e) {
            JOptionPane.showMessageDialog(null,"the input file is not well-formed.\n"+e.getMessage()+"\nplease check the file");
        }
       catch (JDOMException e) {
            JOptionPane.showMessageDialog(null,"the file could not be built.\n"+e.getMessage()+"\nplease check the file");
        }
       catch(HeadlessException e){}
    }
    public static void setFeedback(String Server,String jobId,String message){
       try{
           String s =URLEncoder.encode(message, "UTF-8");
           URL server = new URL(Server+"send_feedback.php?id="+jobId+"&com=LMC&msg="+s);
           URLConnection in = server.openConnection();
           Document doc = new SAXBuilder("org.apache.xerces.parsers.SAXParser").build(in.getInputStream());
           in.getInputStream().close();
           if(!doc.getRootElement().getChildText("msg").equalsIgnoreCase("Feedback Updated"))
               JOptionPane.showMessageDialog(null, "Feedback updated failed\n"+doc.getRootElement().getChildText("msg"));
       }
       catch (IOException e) {
            JOptionPane.showMessageDialog(null,"an I/O error occurred.\n"+e.getMessage());
        }
       catch (JDOMException e) {
            JOptionPane.showMessageDialog(null,"the file could not be built.\n"+e.getMessage()+"\nplease check the file");
        }
       catch(HeadlessException e){}
    }

    public static void sendOutput(String server,String jobId, Document doc){
        try{
            URL serverURL = new URL(server+"send_output.php");
            URLConnection output = serverURL.openConnection();
            output.setDoOutput(true);
            OutputStream  out = output.getOutputStream();

            ClientHttpRequest re = new ClientHttpRequest(serverURL);
            re.setParameter("id", jobId);
            re.setParameter("com", "LMC");
            re.setParameter("data", new XMLOutputter(org.jdom.output.Format.getPrettyFormat().setTextMode(TextMode.NORMALIZE).setIndent("    ").setOmitEncoding(true)).outputString(doc));


            Document responce = new SAXBuilder("org.apache.xerces.parsers.SAXParser").build(re.post());
            JOptionPane.showMessageDialog(null, responce.getRootElement().getChildText("msg"));
       }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
