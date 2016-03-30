package com.cefalo.rxtest.simple;

import rx.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Example1 {
    public static void main(String[] args) throws InterruptedException {
        basicEvent();
    }

    private static void basicEvent() throws InterruptedException {
        Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS);
        interval.subscribe(l -> {
            System.out.println("Interval " + l);
        });

        Observable<Long> fixed = interval.take(5);
        fixed.subscribe(l -> {
            System.out.println("Fixed " + l);
        });

        Thread.sleep(10 * 1000);
    }


    private static void collectionAndRx() {
        List<Integer> integers = Arrays.asList(2, 1, 3, 4, 5, 6, 7, 8);
        List<Integer> filtered = integers.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
        System.out.println(integers);
        System.out.println(filtered);

        Observable<Integer> numbers = Observable.from(integers);
        numbers.subscribe(num -> {
            System.out.println("Number is " + num);
        });

        Observable<Integer> filteredObservables = numbers.filter(integer -> integer % 2 == 0);
        filteredObservables.subscribe(System.out::println);
    }
}
