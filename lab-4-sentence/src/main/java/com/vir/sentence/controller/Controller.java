package com.vir.sentence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.vir.sentence.service.SentenceService;

@RestController
public class Controller {
	
	@Autowired SentenceService sentenceService;
	
	 @GetMapping("/sentence")
	  public @ResponseBody String getSentence() {
		 long start = System.currentTimeMillis();
		 String output = 
			"<h3>Some Sentences</h3><br/>" +	  
			sentenceService.buildSentence() + "<br/><br/>" +
			sentenceService.buildSentence() + "<br/><br/>" +
			sentenceService.buildSentence() + "<br/><br/>" +
			sentenceService.buildSentence() + "<br/><br/>" +
			sentenceService.buildSentence() + "<br/><br/>"
			;
		 long end = System.currentTimeMillis();
			return output + "Elapsed time (ms): " + (end - start);
	  }

}
