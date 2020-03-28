package com.vir.sentence.service;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.vir.sentence.dao.AdjectiveClient;
import com.vir.sentence.dao.ArticleClient;
import com.vir.sentence.dao.NounClient;
import com.vir.sentence.dao.SubjectClient;
import com.vir.sentence.dao.VerbClient;
import com.vir.sentence.domain.Word;
import com.vir.sentence.domain.Word.Role;

import rx.Observable;
import rx.schedulers.Schedulers;

@Service
public class WordServiceImpl implements WordService{
	@Autowired VerbClient verbService;
	@Autowired SubjectClient subjectService;
	@Autowired ArticleClient articleService;
	@Autowired AdjectiveClient adjectiveService;
	@Autowired NounClient nounService;
	@Autowired Executor executor;	//	Source of threads


	@Override
	@HystrixCommand(fallbackMethod="getFallbackSubject")
	public Observable<Word> getSubject() {
		return Observable.fromCallable(
				() ->  new Word (subjectService.getWord().getWord(), Role.subject)
				).subscribeOn(Schedulers.from(executor));
	}
	
	public Word getFallbackSubject() {
		return new Word("Someone", Role.subject);
	}

	@Override
	@HystrixCommand(fallbackMethod="getFallbackVerb")
	public Observable<Word> getVerb() {
		return Observable.fromCallable(
				() ->  new Word (verbService.getWord().getWord(), Role.verb)
				).subscribeOn(Schedulers.from(executor));
	}
	
	public Word getFallbackVerb() {
		return new Word("verb", Role.verb);
	}

	@Override
	@HystrixCommand(fallbackMethod="getFallbackArticle")
	public Observable<Word> getArticle() {
		return Observable.fromCallable(
				() ->  new Word (articleService.getWord().getWord(), Role.article)
				).subscribeOn(Schedulers.from(executor));
	}
	
	public Word getFallbackArticle() {
		return new Word("the", Role.article);
	}

	@Override
	@HystrixCommand(fallbackMethod="getFallbackAdjective")
	public Observable<Word> getAdjective() {
		return Observable.fromCallable(
				() ->  new Word (adjectiveService.getWord().getWord(), Role.adjective)
				).subscribeOn(Schedulers.from(executor));
	}
	
	public Word getFallbackAdjective() {
		return new Word("", Role.adjective);
	}

	@Override
	@HystrixCommand(fallbackMethod="getFallbackNoun")
	public Observable<Word> getNoun() {
		return Observable.fromCallable(
				() ->  new Word (nounService.getWord().getWord(), Role.noun)
				).subscribeOn(Schedulers.from(executor));
	}	
	
	public Word getFallbackNoun() {
		return new Word("something", Role.noun);
	}
	
	
}
