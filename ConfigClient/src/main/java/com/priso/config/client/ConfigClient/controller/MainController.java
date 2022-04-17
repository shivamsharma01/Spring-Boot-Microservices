package com.priso.config.client.ConfigClient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.priso.config.client.ConfigClient.config.ChannelInfo;
import com.priso.config.client.ConfigClient.dto.ConfigProps;

@RestController
@RefreshScope
public class MainController {

	@Value("${channel.source: No Channel}")
	private String source;

	@Autowired
	private ConfigProps configProps;

	@GetMapping("/info")
	public ChannelInfo getChannelInfo() {
		return new ChannelInfo(source, configProps.getName(), configProps.getUrl());
	}
}
