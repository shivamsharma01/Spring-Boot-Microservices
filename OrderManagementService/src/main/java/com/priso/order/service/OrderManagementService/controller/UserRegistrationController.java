package com.priso.order.service.OrderManagementService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.priso.order.service.OrderManagementService.dto.SellerDto;
import com.priso.order.service.OrderManagementService.service.UserRegistrationService;

import java.util.List;

/**
 * @author -
 *         GreenLearner(https://www.youtube.com/channel/UCaH2MTg94hrJZTolW01a3ZA)
 */

@RestController
public class UserRegistrationController {

	@Autowired
	private UserRegistrationService userRegistrationService;

	Logger logger = LoggerFactory.getLogger(UserRegistrationController.class);

	@PostMapping("/register/seller")
	public String registerAsSeller(@RequestBody SellerDto sellerDto) {
		logger.info("registerAsSeller Called");
		return userRegistrationService.registerSeller(sellerDto);
	}

	@GetMapping("/sellerList")
	public List<SellerDto> getSellersList() {
		logger.info("getSellersList Called");
		return userRegistrationService.getSellersList();
	}
}
