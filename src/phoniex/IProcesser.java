/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phoniex;

import javax.swing.JProgressBar;
import org.jdom.Document;

/**
 *
 * @author Admin
 */
public interface IProcesser {
    public Document process();
    public Document process(double percent);
    public Document getRepos();
    public void setRepos(Document repos);
    public Document getSource() ;
    public void setSource(Document source);
    public void setProgress(JProgressBar progress);

    
}
