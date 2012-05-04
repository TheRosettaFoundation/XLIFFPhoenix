
import org.jdom.Document;
import phoniex.IO.Input;
import phoniex.IO.Output;
import phoniex.Processer;
import phoniex.StatBuilder;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        Document lmc= Input.docLoad("C:/Documents%20and%20Settings/Admin/Desktop/xliff/testData/Sample.lmc");
        Document doc = Input.docLoad("C:/Documents%20and%20Settings/Admin/Desktop/xliff/testData/Symposium6.xlf");
        Processer p = new Processer(lmc, doc);
        Document target = p.process(.75);
        StatBuilder sb =new StatBuilder(target);
        Document stats = sb.build();
        Output.save(target, System.out);

    }

}
