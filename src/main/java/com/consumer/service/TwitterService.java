package com.consumer.service;

import java.util.List;

import com.consumer.model.Twitter;
import com.consumer.model.TwitterDto;

public interface TwitterService {

	void persistTwitter(List<TwitterDto> twitterDtos);
	
	List<Twitter> findAll();

	Twitter save(Twitter twitter);

}
