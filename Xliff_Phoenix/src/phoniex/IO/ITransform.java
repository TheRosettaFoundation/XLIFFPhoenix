/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phoniex.IO;

import org.jdom.Document;

/**
 *
 * @author Admin
 */
public interface ITransform {

    void setDoc(Document doc);

    Document transform();

}
