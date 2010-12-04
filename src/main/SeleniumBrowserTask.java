package main;

import static main.SeleniumLuncher.configuration;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

/**
 * This represents the task needs to be executed.
 *
 * @author Cobalt
 *
 */
public class SeleniumBrowserTask {

	private Selenium browser;

	/**
	 * The main entry point.
	 *
	 * @throws InterruptedException
	 */
	public void doWork() throws InterruptedException {

		startBrowser();

		execute();

		synchronized (this) {
			this.wait(5000);
		}

		browser.close();

	}

	/**
	 * Starts a new browser driver.
	 */
	private void startBrowser() {
		String browserPath = configuration
				.getProperty("selenium_server_browser_path");
		browserPath = (browserPath != null && browserPath.length() > 0) ? " "
				+ browserPath : "";

		browser = new DefaultSelenium("localhost", Integer
				.parseInt(configuration.getProperty("selenium_server_port")),
				"*" + configuration.getProperty("selenium_server_browser_type")
						+ browserPath, configuration
						.getProperty("selenium_server_target_url"));
		browser.start();
	}

	/**
	 * Executes the task with the browser driver.
	 *
	 * @throws InterruptedException
	 */
	private void execute() throws InterruptedException {
		browser.open(configuration
				.getProperty("selenium_server_target_address"));

		browser.waitForPageToLoad(configuration
				.getProperty("page_load_wait_time"));

		browser.type("in_tx_username", configuration.getProperty("user"));

		browser.type("in_pw_userpass", configuration.getProperty("pass"));

		browser.click("in_bu_Login");

		browser.waitForPageToLoad(configuration
				.getProperty("page_load_wait_time"));

		if (configuration.getProperty("selenium_server_browser_type").equals(
				"opera")) {
			browser.select(configuration.getProperty("opera_menu_name"),
					configuration.getProperty("opera_menu_item_name"));
		} else if (configuration.getProperty("selenium_server_browser_type")
				.equals("firefox")) {

			browser.click(configuration.getProperty("firefox_menu_1"));

			synchronized (this) {
				this.wait(500);
			}
			browser.click(configuration.getProperty("firefox_menu_2"));

			// browser.open(configuration.getProperty("firefox_portlet_url"));

		}

		browser.waitForPageToLoad(configuration
				.getProperty("page_load_wait_time"));

		browser.click(configuration.getProperty("portlet_start_button_name"));
	}

}
