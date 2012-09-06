/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phoniex.GUI;

import phoniex.PhoenixApp;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JTextPane;
import net.boplicity.xmleditor.XmlEditorKit;

/**
 *
 * @author Admin
 */
public class WTextPane extends JTextPane{
    static boolean showWatermark = true;

    public static boolean isShowWatermark() {
        return showWatermark;
    }

    public static void setShowWatermark(boolean showWatermark) {
        WTextPane.showWatermark = showWatermark;
    }

    public WTextPane() {
        super();
        this.setEditorKitForContentType("text/xml", new XmlEditorKit());
        this.setContentType("text/xml");
    }
    public static Image water=Toolkit.getDefaultToolkit().getImage(PhoenixApp.class.getResource("resources/Xliff_Phoenix_Watermark.png"));

    @Override
    protected void paintComponent(Graphics g) {
         if(isShowWatermark()){
             setOpaque(false);
             setBackground(new Color(0,0,0,0));
             g.drawImage(water, g.getClipBounds().x, g.getClipBounds().y,g.getClipBounds().width, g.getClipBounds().height, Color.white, this);
         }
         else{
             setOpaque(true);
             setBackground(Color.WHITE);
         }
         super.paintComponent(g);
         
    
    }



    
}
   
