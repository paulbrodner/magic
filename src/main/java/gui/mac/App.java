package gui.mac;

import org.sikuli.script.FindFailed;

/**
 * @author Paul.Brodner
 */
public class App extends gui.Base
{

    /**
     * This constructor will define the prefix for default archive collection of
     * images
     * 
     * @param rootImageFolder
     * @param name
     * @param title
     */
    public App(String rootImageFolder, String name, String title)
    {
        super("mac." + rootImageFolder, name, title);
    }

    public void quit() throws FindFailed
    {
        this.rightClick(getAppImage(), 0);
        this.touch("quit_context.png");
    }
}
