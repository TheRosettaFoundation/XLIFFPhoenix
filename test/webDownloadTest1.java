
import javax.swing.JOptionPane;
import phoniex.IO.Input;
import phoniex.IO.Output;

/**
 *
 * @author Admin
 */
public class webDownloadTest1 {

    public static void main(String[] args){
        try{
            args = new String[]{//for testing in netbeans
                "http://193.1.97.50/locConnect/",//hostName
                "9b80260aa8"};//jobID

            Output.save(Input.getLMC(args[0], args[1]), System.out);
            

       }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }

    }

   
}
