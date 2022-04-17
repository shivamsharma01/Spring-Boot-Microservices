package com.priso.diseases.DiseaseService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diseases")
public class MainController {

	@GetMapping("/message")
	public String getResponse() {
		return "Response from DiseaseService";
	}
}