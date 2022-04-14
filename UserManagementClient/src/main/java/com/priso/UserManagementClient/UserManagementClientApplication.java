package com.priso.UserManagementClient;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserManagementClientApplication {

	public static void main(String[] args) throws IOException {
		// to provide SSL validation in JVM
		// System.setProperty("javax.net.ssl.trustStore",
		// "E:\\Spring-Boot-Microservices\\cert\\ssl_server.jks");
		// System.setProperty("javax.net.ssl.trustStorePassword", "priso_root");

		SpringApplication.run(UserManagementClientApplication.class, args);
	}

}
