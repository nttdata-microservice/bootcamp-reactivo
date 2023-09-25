package com.nttdata.reactivo;

import com.nttdata.reactivo.service.AccountService;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CompletableExample {

  private final AccountService accountService;

  public void runExample() {
    //Simulando un Endpoint o un métodos de base de datos
    Completable completable = accountService.deleteById(10);

    completable.subscribe(new CompletableObserver() {
      @Override
      public void onSubscribe(@NonNull Disposable d) {
        System.out.println("Call to Subscribe");
      }

      @Override
      public void onComplete() {
        System.out.println("HttpStatus: 204");
      }

      @Override
      public void onError(@NonNull Throwable e) {
        System.out.println("codeError: NT120");
        System.out.println("Description: Error en tiempo de ejecución");
        System.out.println(e.getMessage());
      }
    });

  }




}
