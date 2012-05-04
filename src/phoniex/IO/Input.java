/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phoniex.IO;
import java.awt.HeadlessException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
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
        try {
         doc = builder.build(file);

        }
        catch (JDOMException e) {
            JOptionPane.showMessageDialog(null, file + " is not well-formed.\n"+e.getMessage()+"\nplease check the file");
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null,file + "could not be built.\n"+e.getMessage()+"\nplease check the file");
        }
        return doc;

        }
    public static Document serverDocLoad(String Server,String jobId){
       Document doc=null;
       try{
           URL server = new URL(Server+"get_job.php?id="+jobId+"&com=LMC");
           URLConnection in = server.openConnection();
           doc = new SAXBuilder("org.apache.xerces.parsers.SAXParser").build(in.getInputStream());
           in.getInputStream().close();
       }
       catch (IOException e) {
            JOptionPane.showMessageDialog(null,"the input file is not well-formed.\n"+e.getMessage()+"\nplease check the file");
        }
       catch (JDOMException e) {
            JOptionPane.showMessageDialog(null,"the file could not be built.\n"+e.getMessage()+"\nplease check the file");
        }
       catch(HeadlessException e){}


       return doc;
    }



    public static Document getJobs(String Server){
       Document doc=null;
       try{
           URL server = new URL(Server+"fetch_job_id.php?com=LMC");
           URLConnection in = server.openConnection();
           doc = new SAXBuilder("org.apache.xerces.parsers.SAXParser").build(in.getInputStream());
           in.getInputStream().close();
       }
       catch (IOException e) {
            JOptionPane.showMessageDialog(null,"the input file is not well-formed.\n"+e.getMessage()+"\nplease check the file");
        }
       catch (JDOMException e) {
            JOptionPane.showMessageDialog(null,"the file could not be built.\n"+e.getMessage()+"\nplease check the file");
        }
       catch(HeadlessException e){
        JOptionPane.showMessageDialog(null,e.getMessage());
       }


       return doc;
    }

    public static Document getLMC(String Server,String jobId){
       Document doc=null;
       try{
           URL server = new URL(Server+"get_resource.php?id="+jobId);
           URLConnection in = server.openConnection();
           doc = new SAXBuilder("org.apache.xerces.parsers.SAXParser").build(in.getInputStream());
           in.getInputStream().close();
       }
       catch (IOException e) {
            JOptionPane.showMessageDialog(null,"the input file is not well-formed.\n"+e.getMessage()+"\nplease check the file");
        }
       catch (JDOMException e) {
            JOptionPane.showMessageDialog(null,"the file could not be built.\n"+e.getMessage()+"\nplease check the file");
        }
       catch(HeadlessException e){}


       return doc;
    }
}
