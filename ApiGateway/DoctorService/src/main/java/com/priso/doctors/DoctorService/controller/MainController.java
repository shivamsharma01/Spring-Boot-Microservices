package com.priso.doctors.DoctorService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctors")
public class MainController {

	@GetMapping("/message")
	public String getResponse() {
		return "Response from DoctorService";
	}
}