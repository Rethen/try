package com.fernandocejas.android10.sample.domain;



import org.junit.Test;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by then on 2017/1/18.
 */

public class RxJavaTest {
    @Test
    public void testRx() {
        String[] strings = new String[]{"1", "2", "3", "5", "6", "8", "1", "4","5"};
        Observable.from(strings)
                .flatMap((Func1) (s) ->Observable.just(new Integer((String)s)))
                .filter(i -> (int) i > 3)
                .toSortedList()
                .subscribe(s -> System.out.println("S:" + s));
    }
}
