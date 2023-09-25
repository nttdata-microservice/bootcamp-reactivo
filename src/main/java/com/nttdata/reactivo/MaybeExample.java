package com.nttdata.reactivo;

import com.nttdata.reactivo.model.Account;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class MaybeExample {
  private Maybe<Account> getAccountById(Integer id) {
    return Maybe.create(emitter -> {
      if (id < 0) {
        emitter.onError(new Throwable("Constraint detected"));
      } else if ( id < 100) {
        emitter.onSuccess(new Account(id, "Diaz", "Bueno"));
      } else {
        emitter.onComplete();
      }
    });
  }
  public void runEndpoint() {
    Maybe<Account> maybe = this.getAccountById(205);
    maybe.subscribe(new MaybeObserver<Account>() {
      @Override
      public void onSubscribe(@NonNull Disposable d) {
        System.out.println("Subscribing Account");
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
