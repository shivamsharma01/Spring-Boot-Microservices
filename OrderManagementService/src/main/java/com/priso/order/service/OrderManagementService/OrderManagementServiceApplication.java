package com.priso.order.service.OrderManagementService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;


// http://localhost:8085/hystrix.stream
// http://localhost:8085/hystrix -> add http://localhost:8085/hystrix.stream || any app name || 2000 delay

@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
public class OrderManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderManagementServiceApplication.class, args);
	}

}
