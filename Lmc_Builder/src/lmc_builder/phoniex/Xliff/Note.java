/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lmc_builder.phoniex.Xliff;

import org.jdom.Comment;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author sean
 */
public class Note extends XliffElement{

    private Note(){
        super("note");
    }
    public Note(String s){
        super("note");
        addText(s);
    }
    
   public Note(MetaData meta){
        this();
        XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
        addContent(new Comment(out.outputString(meta)));
    }

}
