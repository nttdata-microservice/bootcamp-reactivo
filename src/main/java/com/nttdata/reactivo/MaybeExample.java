package com.nttdata.reactivo;

import com.nttdata.reactivo.model.Account;
import com.nttdata.reactivo.service.AccountService;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MaybeExample {

  private final AccountService accountService;

  public void runEndpoint() {
    Maybe<Account> maybe = accountService.getAccountByIdMaybe(600);

    maybe.subscribe(new MaybeObserver<Account>() {
      @Override
      public void onSubscribe(@NonNull Disposable d) {
        System.out.println("Maybe - Subscribing Account");
      }
      @Override
      public void onSuccess(@NonNull Account account) {
        System.out.println("Account: " + account);
      }
      @Override
      public void onError(@NonNull Throwable e) {
        System.out.println("Error: " + e.getMessage());
        System.out.println("HttpStatus: 500");
      }
      @Override
      public void onComplete() {
        System.out.println("HttpStatus: 404");
      }
    });
  }

  public void runExample() {
    Maybe<String> maybe = createMaybe();
    maybe.subscribe(new MaybeObserver<String>() {
      @Override
      public void onSubscribe(@NonNull Disposable d) {

      }

      @Override
      public void onSuccess(@NonNull String s) {
        System.out.println(s);
      }

      @Override
      public void onError(@NonNull Throwable e) {

      }

      @Override
      public void onComplete() {
        System.out.println("NO CONTENT");
      }
    });
  }
  private Maybe<String> createMaybe() {
    return Maybe.create(emitter -> {
      var newContent = readFile();
      if (newContent != null) {
        emitter.onSuccess(newContent);
      } else {
        emitter.onComplete();
      }
    });
  }
  private String readFile() {
    return "Content File";
  }
}
