
import javax.swing.DefaultListModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class bugtest {
 static DefaultListModel current = new DefaultListModel();
 public static void main(String[] args){
     current.addElement("test");
     DefaultListModel lmc =new DefaultListModel();
        for(int i=0;i!=current.getSize();i++){
            Object o = current.get(i);
            lmc.addElement(o);
        }
 }
}
