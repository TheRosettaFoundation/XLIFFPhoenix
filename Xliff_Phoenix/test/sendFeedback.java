
import phoniex.IO.Output;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class sendFeedback {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String s= "Loading Resources";
        String id = "2dcc8a6495";
        String server = "http://10.100.13.155/locConnect/";
        Output.setFeedback(server, id, s);
    }

}
