/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lmc_builder.phoniex.Lmc;

import java.util.List;
import org.jdom.Document;

/**
 *
 * @author Admin
 */
public interface ILmcBuilder {

    Document build();

    Document build(Document doc);

    Document build(List<Document> docs);
    Document reformat(Document doc);
    public void setAuthor(String author);
}
