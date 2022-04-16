package com.priso.registrationService.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.priso.registrationService.dto.SellerDto;
import com.priso.registrationService.service.RegistrationService;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;

	Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	@PostMapping("/addSeller")
	public String addSeller(@RequestBody SellerDto sellerDto) {
		logger.info("addSeller Called");
		return registrationService.addSeller(sellerDto);
	}

	@GetMapping("/sellersList")
	public List<SellerDto> getSellersList() {
		logger.info("getSellersList Called");
		return registrationService.getSellersList();
	}
	
	@GetMapping("/")
	public String test() {
		logger.info("test Called");
		return "Hystrix Registration Service is UP";
	}
}
