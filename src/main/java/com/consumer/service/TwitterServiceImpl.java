package com.consumer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.consumer.model.Twitter;
import com.consumer.model.TwitterDao;
import com.consumer.model.TwitterDto;
import com.consumer.repository.TwitterRepository;

@Service
public class TwitterServiceImpl implements TwitterService {
	private final TwitterRepository twitterRepository;
	private final ModelMapper modelMapper;

	public TwitterServiceImpl(TwitterRepository twitterRepository, ModelMapper modelMapper) {

		this.twitterRepository = twitterRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public void persistTwitter(List<TwitterDto> twitterDtos) {
		for (TwitterDto twitterDto : twitterDtos) {
			TwitterDao twitterDao = modelMapper.map(twitterDto, TwitterDao.class);
			String geners = twitterDao.getGenres().stream().collect(Collectors.joining(","));
			Twitter twitter = new Twitter(twitterDao.getId(), twitterDao.getTweet(), twitterDao.getHashTag(), geners);
			Twitter persistedFoodOrder = twitterRepository.save(twitter);

			System.out.println("twitter persisted " + persistedFoodOrder);
		}

	}

	@Override
	public List<Twitter> findAll() {
		
		return twitterRepository.findAll();
	}

	@Override
	public Twitter save(Twitter twitter) {
		
		return twitterRepository.save(twitter);
	}

}
