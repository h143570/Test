package main;

import java.net.Socket;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpClientConnection;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.protocol.RequestConnControl;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestExpectContinue;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;
import org.apache.http.util.EntityUtils;

public class LowerLevelApproach {

	public static void main(String[] args) throws Exception {

		HttpParams params = new BasicHttpParams();
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params,
				"iso-8859-1, utf-8, utf-16, *;q=0.1");
		HttpProtocolParams.setUserAgent(params,
				"Opera/10.00 (Windows NT 6.0; U; en) Presto/2.2.0");

		// HttpProtocolParams.setUseExpectContinue(params, true);

		BasicHttpProcessor httpproc = new BasicHttpProcessor();
		// Required protocol interceptors
		httpproc.addInterceptor(new RequestContent());
		httpproc.addInterceptor(new RequestTargetHost());
		// Recommended protocol interceptors
		httpproc.addInterceptor(new RequestConnControl());
		httpproc.addInterceptor(new RequestUserAgent());
		httpproc.addInterceptor(new RequestExpectContinue());

		HttpRequestExecutor httpexecutor = new HttpRequestExecutor();

		HttpContext context = new BasicHttpContext(null);
		HttpHost host = new HttpHost("portal.cat-szeged.hu", 9090);

		DefaultHttpClientConnection conn = new DefaultHttpClientConnection();
		ConnectionReuseStrategy connStrategy = new DefaultConnectionReuseStrategy();

		context.setAttribute(ExecutionContext.HTTP_CONNECTION, conn);
		context.setAttribute(ExecutionContext.HTTP_TARGET_HOST, host);

		try {

			String target = "/portal/server.pt?";

			if (!conn.isOpen()) {
				Socket socket = new Socket(host.getHostName(), host.getPort());
				conn.bind(socket, params);
			}
			BasicHttpRequest request = new BasicHttpRequest("GET", target);
			System.out.println(">> Request URI: "
					+ request.getRequestLine().getUri());

			request.setParams(params);
			httpexecutor.preProcess(request, httpproc, context);
			HttpResponse response;
			response = httpexecutor.execute(request, conn, context);
			response.setParams(params);
			httpexecutor.postProcess(response, httpproc, context);

			if (!connStrategy.keepAlive(response, context)) {
				conn.close();
			} else {
				System.out.println("Connection kept alive...");
			}

			// /////////////////////////////////////////////////////////////////

			BasicHttpEntityEnclosingRequest request2 = new BasicHttpEntityEnclosingRequest(
					"POST", target);
			((BasicHttpEntityEnclosingRequest) request2).expectContinue();
			System.out.println(">> Request URI: "
					+ request2.getRequestLine().getUri());

			StringBuffer sb = new StringBuffer();
			sb.append("in_hi_space=Login&");
			sb.append("in_hi_spaceID=0&");
			sb.append("in_hi_control=Login&");
			sb.append("in_hi_dologin=true&");
			sb.append("in_tx_username=Administrator&");
			sb.append("in_pw_userpass=&");
			sb.append("in_se_authsource=");

			StringEntity se = new StringEntity(sb.toString(), "UTF-8");

			request2.setEntity(se);

			request2.setParams(params);
			for (Header header : response.getAllHeaders()) {
				if (header.getName().toLowerCase().indexOf("cookie") != -1) {
					request2.addHeader("Cookie", header.getValue().replaceAll(
							"; path=/", ""));
				}
			}
			request2.addHeader("Cookie2", "$Version=1");

			request2.addHeader("Referer",
					"http://portal.cat-szeged.hu:9090/portal/server.pt?");
			request2.addHeader("Content-Type",
					"application/x-www-form-urlencoded");

			request2
					.addHeader(
							"Accept",
							"text/html, application/xml;q=0.9, application/xhtml+xml, image/png, image/jpeg, image/gif, image/x-xbitmap, */*;q=0.1");

			request2.addHeader("Connection", "Keep-Alive, TE");
			request2.addHeader("TE",
					"deflate, gzip, chunked, identity, trailers");
			request2.addHeader("Accept-Encoding",
					"deflate, gzip, x-gzip, identity, *;q=0");
			request2.addHeader("Accept-Charset",
					"iso-8859-1, utf-8, utf-16, *;q=0.1");
			request2.addHeader("Accept-Language", "hu-HU,hu;q=0.9,en;q=0.8");


			httpexecutor.preProcess(request2, httpproc, context);
			HttpResponse response2 = httpexecutor.execute(request2, conn,
					context);
			response2.setParams(params);
			httpexecutor.postProcess(response2, httpproc, context);

			System.out.println("<< Response: " + response2.getStatusLine());
			System.out.println(EntityUtils.toString(response2.getEntity()));
			System.out.println("==============");
			if (!connStrategy.keepAlive(response2, context)) {
				conn.close();
			} else {
				System.out.println("Connection kept alive...");
			}

		} finally {
			conn.close();
		}
	}
}
