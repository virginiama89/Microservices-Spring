package com.vir.sentence.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vir.sentence.domain.Sentence;
import com.vir.sentence.domain.Word;

import rx.Observable;

@Service
public class SentenceServiceImpl implements SentenceService	{

	@Autowired WordService wordService;

	/**
	 * Assemble a sentence by gathering random words of each part of speech:
	 */
	public String buildSentence() {
		
		Sentence sentence = new Sentence();
		System.out.println("frase creada");
		// Lanza las llamadas a los servicios hijos, usando observables para las respuestas de cada uno 
		List<Observable<Word>> observables = createObservables();
		System.out.println("creados los observables");	
		// Vamos a usar la clase CountDownLatch para detectar cuando todas las llamadas están completas, lo inicializo con el número de llamadas que tiene que comprobar
		CountDownLatch latch = new CountDownLatch(observables.size());
		System.out.println("creado el countdown");	
		//	Jutnto todos los observables en uno, y nos suscribimos a este (este código se ejecutará cuando cada uno de los servicios finalice)
		Observable.merge(observables)
			.subscribe(
				//	Cuando cada uno de los servicios se ha completado, añado la palabra a la lista y decremento el contador
				//	to the sentence, and decrement the CountDownLatch:
				(word) -> {
					System.out.println("palabra: " + word.toString());
					sentence.add(word);
					latch.countDown();
		        }
		);
		System.out.println("juntados los observables");
		// Mientras no hayan finalizado
		waitForAll(latch);
		System.out.println("ya no espero más");
		//Devuelvo la frase completa
		return sentence.toString();
	}
	
	/**
	 * Por útlimo, necesitaremos esperar a que las 5 llamadas se completen, así que espero 
	 */
	private void waitForAll(CountDownLatch latch) {
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Llamamos a los servicios de cada palabra, y nos devuelven los observables que metemos en una lista para que estén ordenados 
	 */
	private List<Observable<Word>> createObservables(){
		List<Observable<Word>> observables = new ArrayList<>();
		observables.add(wordService.getSubject());
		observables.add(wordService.getVerb());
		observables.add(wordService.getArticle());
		observables.add(wordService.getAdjective());
		observables.add(wordService.getNoun());
		return observables;
	}

}
