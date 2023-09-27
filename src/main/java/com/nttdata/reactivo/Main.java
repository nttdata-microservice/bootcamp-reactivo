package com.nttdata.reactivo;

import com.nttdata.reactivo.service.AccountService;
import io.reactivex.rxjava3.core.Flowable;

public class Main {
  public static void main(String[] args) {
    System.out.println("Hello Developers");

    AccountService accountService = new AccountService();

    /*SingleExample singleExample = new SingleExample(accountService);
    singleExample.runEndpoint();*/

    /*MaybeExample maybeExample = new MaybeExample(accountService);
    maybeExample.runEndpoint();*/

    /*CompletableExample completableExample = new CompletableExample(accountService);
    completableExample.runExample();*/

    FlowableExample flowableExample = new FlowableExample(accountService);
    flowableExample.runEndpoint();


  }
}
