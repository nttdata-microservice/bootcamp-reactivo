package com.nttdata.reactivo;

import com.nttdata.reactivo.service.AccountService;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.AllArgsConstructor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class FlowableExample {

  private final AccountService accountService;

  public void runEndpoint() {
    Flowable<String> flowable = Flowable.create(emitter -> {
      emitter.onNext("Uno");
      emitter.onNext("dos");
      emitter.onComplete();
    }, BackpressureStrategy.BUFFER);

    Flowable<Integer> flowableInt = Flowable.just(1, 2, 3, 4, 5, 6, 7);

    flowable.subscribe(new Subscriber<String>() {
      @Override
      public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
      }

      @Override
      public void onNext(String s) {
        System.out.println(s);
      }

      @Override
      public void onError(Throwable throwable) {
        throwable.printStackTrace();
      }

      @Override
      public void onComplete() {
        System.out.println("Flowable completed");
      }
    });

    flowableInt.subscribe(new FlowableSubscriber<Integer>() {
      @Override
      public void onSubscribe(@NonNull Subscription s) {
        s.request(Long.MAX_VALUE);
      }

      @Override
      public void onNext(Integer integer) {
        System.out.println(integer);
      }

      @Override
      public void onError(Throwable throwable) {
        throwable.printStackTrace();
      }

      @Override
      public void onComplete() {
        System.out.println("Flowable completed");
      }
    });

    List<String> names = Arrays.asList("Juan", "Jose", "Maria");
    Flowable<String> flowableStr = Flowable.fromIterable(names);

    flowableStr
        .subscribeOn(Schedulers.io())
        .subscribe(System.out::println);
  }

}
