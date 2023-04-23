package com.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.consumer.model.Twitter;

@Repository
public interface TwitterRepository extends JpaRepository<Twitter, Long> {

}
