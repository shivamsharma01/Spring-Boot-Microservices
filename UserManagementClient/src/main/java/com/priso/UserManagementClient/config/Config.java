package com.priso.UserManagementClient.config;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.Duration;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class Config {

	private static final String baseUrl = "https://localhost:8082/springDataDemo/";

	@Bean
	@Qualifier("ssl_disabled")
	@DependsOn("restTemplateBuilder")
	public RestTemplate getRestTemplateBeanNoSSL(RestTemplateBuilder builder) {
		return builder.requestFactory(() -> disableSSl()).uriTemplateHandler(new DefaultUriBuilderFactory(baseUrl))
				.setReadTimeout(Duration.ofMillis(2000)).build();
	}

	@Bean
	@Qualifier("ssl_enabled")
	@DependsOn("restTemplateBuilder")
	public RestTemplate getRestTemplateBeanWithSSL(RestTemplateBuilder builder) {
		return builder.requestFactory(() -> validateSSL()).uriTemplateHandler(new DefaultUriBuilderFactory(baseUrl))
				.setReadTimeout(Duration.ofMillis(2000)).build();
	}

	@Bean
	public MyRequestInterceptor myRequestInterceptor() {
		return new MyRequestInterceptor();
	}

	@Bean
	public MyRequestTemplateCustomizer requestTemplateCustomizer() {
		return new MyRequestTemplateCustomizer();
	}

	@Bean
	@DependsOn("requestTemplateCustomizer")
	public RestTemplateBuilder restTemplateBuilder(RestTemplateCustomizer restTemplateCustomizer) {
		return new RestTemplateBuilder(restTemplateCustomizer);
	}

	private HttpComponentsClientHttpRequestFactory disableSSl() {
		TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
			@Override
			public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
				return true;
			}
		};
		SSLContext sslContext = null;
		try {
			sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
					.build();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);

		return requestFactory;
	}

	private HttpComponentsClientHttpRequestFactory validateSSL() {
		String location = "E:/Spring-Boot-Microservices/cert/ssl_server.jks";
		String pass = "priso_root";
		SSLContext sslContext = null;
		try {
			sslContext = SSLContextBuilder.create()
					.loadTrustMaterial(ResourceUtils.getFile(location), pass.toCharArray()).build();
		} catch (Exception e) {
		}
		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new LocalHostnameVerifier());
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

		return requestFactory;
	}

	private class LocalHostnameVerifier implements HostnameVerifier {
		@Override
		public boolean verify(String s, SSLSession sslSession) {
			return "localhost".equalsIgnoreCase(s) || "127.0.0.1".equals(s);
		}
	}
}
