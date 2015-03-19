package magic;

import java.io.File;
import java.net.URL;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Extend this class in order to use mAgic functionalities
 * 
 * @author Paul Brodner
 */
public class Gui extends Screen
{
    protected static Log logger = onThisClass();
    private String appFolder = "";
    private String appIcon = "";
    private String appTitle = "";

    /**
     * Define the rootImageFolder where all unique images resides for this
     * particular application
     * The <name> of the application also an image; and the <title>
     * 
     * @param name
     * @param title
     * @throws Exception
     */
    public Gui(String appIcon, String appTitle) throws Exception
    {
        super();
        URL resource = getClass().getClassLoader().getResource(getApplicationName());
        if (resource == null)
        {
            throw new Exception("Create and initialize a Resource folder first: " + getApplicationName());
        }
        logger.info("Use root folder: " + resource.getPath());
        setAppFolder(resource.getPath());
        setAppIcon(appIcon);
        setAppTitle(appTitle);
    }

    /**
     * This will return the logger of the caller
     * 
     * @return logger of the class name
     */
    protected static Log onThisClass()
    {
        StackTraceElement thisCaller = Thread.currentThread().getStackTrace()[2];
        return LogFactory.getLog(thisCaller.getClassName());
    }

    protected String getApplicationName()
    {
        return this.getClass().getSimpleName();
    }

    protected void setAppFolder(String rootFolder)
    {
        this.appFolder = rootFolder;
    }

    /**
     * Use this to get the images within application folder
     * 
     * @param image
     * @return String image path from app folder
     */
    public String fromApp(String image)
    {
        return new File(getAppFolder(), image).getPath();
    }

    /**
     * Open application using defined image
     * 
     * @throws FindFailed
     */
    public void open() throws FindFailed
    {
        try
        {
            click(fromApp(getAppIcon()), 0);
            logger.info("Waiting for '" + getAppTitle() + "' title...");
            wait(fromApp(getAppTitle()));

        }
        catch (FindFailed e)
        {
            System.out.println("Could not initialize Application '" + getApplicationName() + "' based on: " + getAppIcon());
            throw e;
        }
    }

    /**
     * This method will return the path of archive resources
     * 
     * @return appFolder
     */
    public String getAppFolder()
    {
        return appFolder;
    }

    public void minimize() throws FindFailed
    {
        click("minimize.png", 0);
    }

    public void maximize() throws FindFailed
    {
        click("maximize.png", 0);
    }

    public void close() throws FindFailed
    {
        click("close.png", 0);
    }

    /**
     * Use this method to click on App Images
     * 
     * @param image
     * @throws FindFailed
     */
    public void touch(String image) throws FindFailed
    {
        logger.info("Touch based on image: " + image);
        click(fromApp(image), 0);
    }

    public String getAppIcon()
    {
        return appIcon;
    }

    public void setAppIcon(String appIcon)
    {
        this.appIcon = appIcon;
    }

    public String getAppTitle()
    {
        return appTitle;
    }

    public void setAppTitle(String appTitle)
    {
        this.appTitle = appTitle;
    }
}
