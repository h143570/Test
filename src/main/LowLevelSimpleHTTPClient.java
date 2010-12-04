package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

public class LowLevelSimpleHTTPClient {

	public static final Properties configuration = new Properties();
	private static String portletActionButtonId;
	private static String portalBase;
	private static String portalUsername;
	private static String portalPassword;
	private static String portletActionName;
	private static String serverAddress;

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		configuration.load(new FileInputStream(
				"resources/configuration.properties"));

		portletActionButtonId = configuration
				.getProperty("PORTLET_ACTION_BUTTON_id");
		portalBase = configuration.getProperty("PORTAL_BASE");
		portalUsername = configuration.getProperty("PORTAL_USSERNAME");
		portalPassword = configuration.getProperty("PORTAL_PASSWORD");
		portletActionName = configuration.getProperty("PORTLET_ACTION_NAME");
		serverAddress = configuration.getProperty("SERVER_ADDRESS");

		URL url = new URL(serverAddress + portalBase);

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.connect();

		BufferedReader in = new BufferedReader(new InputStreamReader(connection
				.getInputStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null) {
			// System.out.println(inputLine);
		}

		Map<String, List<String>> headers = connection.getHeaderFields();

		Map<String, List<String>> headersUpdated = new HashMap<String, List<String>>();

		for (Entry<String, List<String>> header : headers.entrySet()) {
			if (header.getKey() != null) {
				if (!header.getKey().equalsIgnoreCase("Cache-Control")) {
					if (!header.getKey().equalsIgnoreCase("Last-Modified")) {
						if (!header.getKey().equalsIgnoreCase("Date")) {
							if (!header.getKey().equalsIgnoreCase("Expires")) {
								if (!header.getKey().equalsIgnoreCase("Server")) {
									if (!header.getKey().equalsIgnoreCase(
											"Pragma")) {
										if (!header.getKey().equalsIgnoreCase(
												"Content-Length")) {
											if (!header.getKey().startsWith(
													"X-")) {
												if ("Set-Cookie"
														.equalsIgnoreCase(header
																.getKey())) {
													List<String> sessionCookie = header
															.getValue();
													List<String> sessionCookieUpdated = new ArrayList<String>();
													for (String cookie : sessionCookie) {
														if (cookie
																.endsWith("; path=/")) {
															sessionCookieUpdated
																	.add(cookie
																			.replaceAll(
																					"; path=/",
																					""));
														} else {
															sessionCookieUpdated
																	.add(cookie);
														}
													}
													headersUpdated
															.put("Cookie",
																	sessionCookieUpdated);
													List<String> sessionCookie2 = new ArrayList<String>();
													sessionCookie2
															.add("$Version=1");
													headersUpdated.put(
															"Cookie2",
															sessionCookie2);
												} else {
													headersUpdated.put(header
															.getKey(), header
															.getValue());
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		in.close();

		HttpURLConnection connection2 = (HttpURLConnection) url
				.openConnection();

		connection2.setRequestMethod("POST");
		connection2.setInstanceFollowRedirects(false);

		connection2.setDoOutput(true);

		System.out.println(connection2.getDoInput());
		System.out.println(connection2.getDoOutput());

		for (Entry<String, List<String>> header : headersUpdated.entrySet()) {
			StringBuffer sb = new StringBuffer();

			for (String listValue : header.getValue()) {
				sb.append(listValue);
				sb.append(",");
			}
			sb.deleteCharAt(sb.length() - 1);

			connection2.setRequestProperty(header.getKey(), sb.toString());
		}
		connection2.setRequestProperty("Referer",
				"http://portal.cat-szeged.hu:9090" + portalBase);
		connection2.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");

		StringBuffer sb = new StringBuffer();
		sb
				.append("in_hi_space=Login&in_hi_spaceID=0&in_hi_control=Login&in_hi_dologin=true&in_tx_username=");
		sb.append(portalUsername);
		sb.append("&in_pw_userpass=");
		sb.append(portalPassword);
		sb.append("&in_se_authsource=");

		String requestBody = sb.toString();

		connection2.setRequestProperty("Content-Length", Integer
				.toString(requestBody.length()));

		connection2.connect();

		OutputStreamWriter out = new OutputStreamWriter(connection2
				.getOutputStream());

		out.write(requestBody);

		out.flush();

		BufferedReader in2 = null;
		try {

			InputStreamReader input = new InputStreamReader(connection2
					.getInputStream());
			in2 = new BufferedReader(input);
			String inputLine2;

			while ((inputLine2 = in2.readLine()) != null) {
				System.out.println(inputLine2);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		out.close();

		Map<String, List<String>> headers2 = connection2.getHeaderFields();

		String location = connection2.getHeaderField("Location");

		URL url3 = new URL(serverAddress + portletActionName);
		HttpURLConnection connection3 = (HttpURLConnection) url3
				.openConnection();

		connection3.setRequestMethod("POST");
		connection3.setInstanceFollowRedirects(false);

		connection3.setDoOutput(true);

		System.out.println(connection3.getDoInput());
		System.out.println(connection3.getDoOutput());

		for (Entry<String, List<String>> header : headersUpdated.entrySet()) {
			StringBuffer sb2 = new StringBuffer();

			for (String listValue : header.getValue()) {
				sb2.append(listValue);
				sb2.append(",");
			}

			sb2.deleteCharAt(sb2.length() - 1);

			if (header.getKey().equalsIgnoreCase("Cookie")) {
				sb2.append("; ");
				sb2.append("plloginoccured=true");
			}

			connection3.setRequestProperty(header.getKey(), sb2.toString());
		}
		connection3.setRequestProperty("Referer", location);
		connection3.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");

		String requestBody2 = portletActionButtonId;

		connection3.setRequestProperty("Content-Length", Integer
				.toString(requestBody2.length()));

		connection3.connect();

		OutputStreamWriter out2 = new OutputStreamWriter(connection3
				.getOutputStream());

		out2.write(requestBody2);

		out2.flush();

		BufferedReader in3 = null;
		try {

			InputStreamReader input = new InputStreamReader(connection3
					.getInputStream());
			in3 = new BufferedReader(input);
			String inputLine3;

			while ((inputLine3 = in3.readLine()) != null) {
				System.out.println(inputLine3);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		out2.close();

		Map<String, List<String>> headers3 = connection3.getHeaderFields();

		for (Entry<String, List<String>> header : headers3.entrySet()) {
			System.out.println(header.getKey() + " : " + header.getValue());
		}

	}
}
