package gui;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

/**
 * @author Paul.Brodner
 *
 */
public class Base extends Screen {
	protected static Log logger = onThisClass();

	private String appFolder = "";
	private String appName = "";
	private String title = "";

	/**
	 * Define the rootImageFolder where all unique images resides for this
	 * particular application
	 * 
	 * The <name> of the application also an image and the <title>
	 * 
	 * @param rootImageFolder
	 * @param name
	 * @param title
	 */
	public Base(String rootImageFolder, String name, String title) {
		super();
		setAppFolder(rootImageFolder);
		setAppName(name);
		setTitle(title);
	}

	/**
	 * This method will return the path of archive resources
	 * 
	 * @return appFolder
	 */
	public String getAppFolder() {
		return appFolder;
	}

	/**
	 * Use this to get the images within app folder
	 * 
	 * @param image
	 * @return String image path from app folder
	 */
	public String fromApp(String image) {
		return getAppFolder() + image;
	}

	protected void setAppFolder(String rootFolder) {
		this.appFolder = new File("img", rootFolder).getPath() + "/";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void open() throws FindFailed {

		try {
			click(getAppImage(), 0);
			System.out.println("Waiting for '" + getTitle() + "' title...");
			wait(fromApp(getTitle()));

		} catch (FindFailed e) {
			System.out.println("Could not initialize Application '" + getAppName() + "' based on " + getAppImage());
			throw e;
		}
	}

	protected String getAppImage() {
		return new File(getAppFolder(), getAppName()).getPath();
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public void minimize() throws FindFailed {
		this.touch("minimize.png");
	}

	public void maximize() throws FindFailed {
		this.touch("maximize.png");
	}

	public void close() throws FindFailed {
		this.touch("close.png");
	}

	/**
	 * Use this method to click on App Images
	 * 
	 * @param image
	 * @throws FindFailed
	 */
	public void touch(String image) throws FindFailed {
		click(fromApp(image), 0);
	}

	/**
	 * This will return the logger of the caller
	 * 
	 * @return logger of the class name
	 */
	protected static Log onThisClass() {
		StackTraceElement thisCaller = Thread.currentThread().getStackTrace()[2];
		return LogFactory.getLog(thisCaller.getClassName());
	}

}
