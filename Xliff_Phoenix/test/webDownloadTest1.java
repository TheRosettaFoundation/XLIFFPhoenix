
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
                "http://10.100.13.155/locConnect/",//hostName
                "9b80260aa8"};//jobID

            Output.save(Input.getLMC(args[0], args[1]), System.out);
            

       }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }

    }

   
}
