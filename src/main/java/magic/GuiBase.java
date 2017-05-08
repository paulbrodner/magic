package magic;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sikuli.script.Screen;

public class GuiBase extends Screen
{
    protected static File rootImageFolder;
    protected static Log logger = onThisClass();

    /**
     * Define the rootImageFolder where all unique images resides for this
     * particular application
     * The <name> of the application also an image and the <title>
     * 
     * @param rootImageFolder
     * @param name
     * @param title
     * @throws Exception
     */
    public GuiBase(String resourceAppPath) throws Exception
    {
        super();
        URL resource = getClass().getClassLoader().getResource(getApplicationName());
        if (resource == null)
        {
            throw new Exception("Create and initialize a Resource folder first: " + getApplicationName());
        }
        logger.info("Use root folder: " + resource.getPath());
        setRootImageFolder(Paths.get(resource.getPath()).toFile());
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

    public File getRootImageFolder()
    {
        return rootImageFolder;
    }

    public void setRootImageFolder(File rootImageFolder)
    {
        this.rootImageFolder = rootImageFolder;
    }

    protected String getApplicationName()
    {
        return this.getClass().getSimpleName().toLowerCase();
    }

}
