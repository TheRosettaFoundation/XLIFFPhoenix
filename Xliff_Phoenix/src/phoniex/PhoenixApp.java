/*
 * PhoenixApp.java
 */

package phoniex;

import java.util.EventObject;
import javax.swing.JOptionPane;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import phoniex.GUI.PhoenixView;

/**
 * The main class of the application.
 */
public class PhoenixApp extends SingleFrameApplication {
    Core core;
    public Core getCore(){return core;}


    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        System.setProperty("file.encoding", "UTF8");
        core=new Core();
         addExitListener(new ExitListener() {
            @Override
            public boolean canExit(EventObject e) {
                boolean bOkToExit = false;
                bOkToExit = JOptionPane.showConfirmDialog(null,"Do you really want to exit?") == JOptionPane.YES_OPTION;
                return bOkToExit;
            }
            @Override
            public void willExit(EventObject event) {

            }
        });

        show(new PhoenixView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of PhoenixApp
     */
    public static PhoenixApp getApplication() {
        return Application.getInstance(PhoenixApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(PhoenixApp.class, args);
    }
}
