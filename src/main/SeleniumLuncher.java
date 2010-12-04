package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Main entry point.
 *
 * @author Cobalt
 *
 */
public class SeleniumLuncher {

	public static final Properties configuration = new Properties();
	private SeleniumServerControlThread controlTread;
	private SeleniumBrowserTask seleniumBrowserTask;

	public static void main(String[] args) throws FileNotFoundException,
			IOException, InterruptedException {

		SeleniumLuncher sbl = new SeleniumLuncher();

		sbl.init();

		try {
			sbl.seleniumBrowserTask.doWork();
		} catch (Exception e) {
			e.getCause();
		}

		sbl.controlTread.shutDownSeleniumServer();

	}

	private void init() throws FileNotFoundException, IOException,
			InterruptedException {
		configuration.load(new FileInputStream("resources/driver.properties"));

		seleniumBrowserTask = new SeleniumBrowserTask();

		controlTread = new SeleniumServerControlThread();
		Thread seleniumServerWorkerThread = new Thread(controlTread);
		seleniumServerWorkerThread.setName("SeleniumServer");
		seleniumServerWorkerThread.start();

		synchronized (this) {
			this.wait(5000);
		}

	}
}
