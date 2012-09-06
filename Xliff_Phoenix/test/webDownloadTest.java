
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JOptionPane;
import org.jdom.Document;
import org.jdom.output.XMLOutputter;
import phoniex.IO.Input;
import phoniex.IO.Output;

/**
 *
 * @author Admin
 */
public class webDownloadTest {

    public static void main(String[] args){
        try{
            args = new String[]{//for testing in netbeans
                "http://10.100.13.155/locConnect/",//hostName
                "2dcc8a6495"};//jobID
            Document doc = Input.docLoad("C:/Documents%20and%20Settings/Admin/Desktop/xliff/testData/Symposium6.xlf");
            Output.sendOutput(args[0], args[1], doc);

       }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }

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
            re.setParameter("data", new XMLOutputter().outputString(doc));


            URLConnection in = serverURL.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(re.post()));
            String line;
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
            }
            rd.close();

       }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
   
}
