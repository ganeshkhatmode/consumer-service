package com.consumer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consumer.model.Twitter;
import com.consumer.service.TwitterService;

@RestController
@RequestMapping("/api/v1/consume")
public class ConsumerController {
	
	@Autowired
	private TwitterService twitterService;
	
	@GetMapping("/")
	public List<Twitter> getTopics() {
		System.out.println("GET TOPIC ");
		return twitterService.findAll();
	}
	
	@PostMapping("/")
	public Twitter save(@RequestBody Twitter twitter) {
		return twitterService.save(twitter);
	}

}
