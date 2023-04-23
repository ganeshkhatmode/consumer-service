package com.consumer.config;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.consumer.model.TwitterDto;
import com.consumer.service.TwitterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Consumer {

	private static final String userTwitterTopic = "${topic.name}";

	private final ObjectMapper objectMapper;
	private final TwitterService foodOrderService;
	
	

	@Autowired
	public Consumer(ObjectMapper objectMapper, TwitterService foodOrderService) {
		this.objectMapper = objectMapper;
		this.foodOrderService = foodOrderService;
	}

	@KafkaListener(topics = userTwitterTopic)
	public void consumeMessage(String message) throws JsonProcessingException {
		System.out.println("message consumed "+ message);

		TwitterDto[] foodOrderDto = null;
		try {
			foodOrderDto = objectMapper.readValue(message, TwitterDto[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		foodOrderService.persistTwitter(Stream.of( foodOrderDto).collect(Collectors.toList()));
	}

}
