/*
 * Lmc_BuilderApp.java
 */

package lmc_builder;

import java.util.EventObject;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class Lmc_BuilderApp extends SingleFrameApplication implements Runnable{

    Core core;
    public Core getCore(){return core;}
    static String exit = "exit";
    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        core= new Core();
        show(new Lmc_BuilderView(this));
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
     * @return the instance of Lmc_BuilderApp
     */
    public static Lmc_BuilderApp getApplication() {
        return Application.getInstance(Lmc_BuilderApp.class);
    }
    public void run(){

        main(new String[]{"dispose"});
    }

    @Override
    public void exit(EventObject event) {
        if(exit=="exit")super.exit(event);
        else if (exit=="dispose")super.getMainFrame().dispose();
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        if(args.length>0) if(args[0]=="dispose")exit=args[0];
        launch(Lmc_BuilderApp.class, args);
    }
}
