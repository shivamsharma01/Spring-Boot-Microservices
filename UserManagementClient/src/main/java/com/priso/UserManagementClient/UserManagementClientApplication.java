package com.priso.UserManagementClient;

import java.io.IOException;

import com.priso.UserManagementClient.restClient.Exchange;
import com.priso.UserManagementClient.restClient.ForEntity;
import com.priso.UserManagementClient.restClient.ForObject;

//@SpringBootApplication
public class UserManagementClientApplication {

	public static void main(String[] args) throws IOException {
		// SpringApplication.run(UserManagementClientApplication.class, args);
		new Exchange().run();
		new ForEntity().run();
		new ForObject().run();
	}

}
