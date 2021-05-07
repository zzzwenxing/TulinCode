package com.tuling;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import rx.Observable;
import rx.Subscriber;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Tulingvip01MsConsumerUser8001ApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void test() {
		//观察者
		Observable<String> producer = Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> subscriber) {
				subscriber.onNext("dog");
				subscriber.onNext("pig");
				subscriber.onCompleted();
			}
		});

		Subscriber<String> consumer = new Subscriber<String>() {
			@Override
			public void onCompleted() {

			}

			@Override
			public void onError(Throwable throwable) {

			}

			@Override
			public void onNext(String s) {
				log.info("我收到一个宠物:{}",s);
			}
		};

		producer.subscribe(consumer);
	}

}
