/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phoniex.IO;

import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.transform.XSLTransformer;
import phoniex.Core;

/**
 *
 * @author Admin
 */
public class TransformerBase implements ITransform {

   
    Document doc;
    XSLTransformer trans;

    public TransformerBase() {
        doc = null;
        trans = null;
    }

    @Override
    public void setDoc(Document doc) {
        this.doc = doc;
    }

    @Override
    public Document transform() {
        try {
            Document temp = (Document) doc.clone();
            List<Element> templist = Core.filter("//*", temp);
            for (Element x : templist) {
                x.setNamespace(Namespace.NO_NAMESPACE);
            }
            return trans.transform(temp);
        } catch (Exception e) {
        }
        return null;
    }

}
