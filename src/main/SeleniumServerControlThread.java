package main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.BindException;
import java.net.ServerSocket;

import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

/**
 * Creates a Selenium Server as a separate thread;
 *
 * @author Cobalt
 *
 */
public class SeleniumServerControlThread implements Runnable {

	SeleniumServer server = null;

	@Override
	public void run() {

		try {
			startSeleniumServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This code snippet was extracted from a web tutorial. I enhanced to use
	 * the global configuration "framework" aka properties file.
	 *
	 * @throws IOException
	 */
	public void startSeleniumServer() throws IOException {
		try {
			ServerSocket serverSocket = new ServerSocket(Integer
					.parseInt(SeleniumLuncher.configuration
							.getProperty("selenium_server_port")));
			serverSocket.close();
			// Server not up, start it
			try {
				RemoteControlConfiguration rcc = new RemoteControlConfiguration();
//				rcc.setFirefoxProfileTemplate(new File(
//						"setup/client/SeleniumProfile"));
				rcc.setTimeoutInSeconds(60);
				rcc.setPort(4444);
				rcc.setMultiWindow(true);
				PrintStream ps = System.out; // backup
				System.setOut(new PrintStream(new FileOutputStream(
						SeleniumLuncher.configuration
								.getProperty("selenium_server_log"))));
				server = new SeleniumServer(false, rcc);
				System.setOut(ps); // restore
			} catch (Exception e) {
				System.err
						.println("Could not create Selenium Server because of: "
								+ e.getMessage());
				e.printStackTrace();
			}
			try {
				server.start();
			} catch (Exception e) {
				System.err
						.println("Could not start Selenium Server because of: "
								+ e.getMessage());
				e.printStackTrace();
			}
		} catch (BindException e) {
			// assume Selenium Server already up
			System.out.println("Selenium server already up, will reuse...");
		}

	}

	public void shutDownSeleniumServer() {
		server.stop();
	}
}
