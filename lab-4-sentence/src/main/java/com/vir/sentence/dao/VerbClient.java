package com.vir.sentence.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.vir.sentence.domain.Word;

@FeignClient("VERB")
public interface VerbClient {

	@GetMapping("/")
	public Word getWord();
	
}
