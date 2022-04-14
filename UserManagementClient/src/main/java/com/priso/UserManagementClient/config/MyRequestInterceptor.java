package com.priso.UserManagementClient.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class MyRequestInterceptor implements ClientHttpRequestInterceptor {

	private Logger logger = LoggerFactory.getLogger(MyRequestInterceptor.class);

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		logger.info("================Request Details================");
		logger.info("URI - {}", request.getURI());
		logger.info("Headers - {}", request.getHeaders());
		logger.info("Method - {}", request.getMethod());
		ClientHttpResponse response = new MyClientHttpResponse(execution.execute(request, body));

		logger.info("================Response Details================");
		logger.info("Status Code - {}", response.getStatusCode());
		logger.info("Body - {}", getResponseBody(response.getBody()));
		logger.info("Headers - {}", response.getHeaders());
		return response;
	}

	private String getResponseBody(InputStream body) {
		StringBuilder builder = new StringBuilder("");
		BufferedReader bis = new BufferedReader(new InputStreamReader(body));
		String inp;
		try {
			while ((inp = bis.readLine()) != null) {
				builder.append(inp);
				builder.append('\n');
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

}
