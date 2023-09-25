package com.nttdata.reactivo;

import io.reactivex.rxjava3.core.Observable;

public class ObserbableExample {
  public void runExample() {
    Observable<String> observable = Observable.create(emitter -> {
      emitter.onNext("Uno");
      emitter.onNext("Dos");
      emitter.onNext("Tres");
      //throw new Exception("Something bad happened");
      emitter.onComplete();
    });
    observable.subscribe(item -> {
      System.out.println(item);
    },throwable -> {
      System.out.println(throwable.getMessage());
    }, () -> {
      System.out.println("On complete");
    });
  }
}
