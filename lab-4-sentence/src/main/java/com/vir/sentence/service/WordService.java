package com.vir.sentence.service;

import com.vir.sentence.domain.Word;

import rx.Observable;

public interface WordService {
	Observable<Word> getSubject();
	Observable<Word> getVerb();
	Observable<Word> getArticle();
	Observable<Word> getAdjective();
	Observable<Word> getNoun();
}
