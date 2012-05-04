/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phoniex;

import org.jdom.Document;

/**
 *
 * @author Admin
 */
public interface IFilter {
public Document refine(Document LMC);
public Document refine();
}
