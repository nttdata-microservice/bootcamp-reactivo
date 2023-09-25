package com.nttdata.reactivo.service;

import com.nttdata.reactivo.model.Account;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Action;

public class AccountService {

  public Single<Account> getAccountByIdSingle(Integer id) {

    return Single.create(emitter -> {
      if (id > 0 && id < 100) {
        emitter.onSuccess(new Account(id, "Diaz", "Bueno"));
      } else {
        emitter.onError(new Throwable("Constraint detected"));
      }
    });
  }
  public Maybe<Account> getAccountByIdMaybe(Integer id) {
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

  public Completable deleteById(Integer id) {
    //return Completable.
    return Completable.fromAction(deleteItemFromDBAction(id));
  }
  public Action deleteItemFromDBAction(Integer id) {
    return new Action() {
      @Override
      public void run() throws Throwable {
        if (id.equals(19)) {
          System.out.println("Deleting item " + id + " from DB");
        } else {
          throw new Throwable("Throwable - Error in Action, Not found id: " + id);
          //throw new RuntimeException("Runtime - Error in Action: 500");
        }
        //throw new RuntimeException("Runtime - Error in Action");
        //throw new Throwable("Throwable - Error in Action");
      }
    };

  }
}
