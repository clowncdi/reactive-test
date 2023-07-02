package study;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Iterator;

// 구독 정보(구독자, 어떤 데이터를 구독할지)
public class MySubscription implements Subscription {

	private Subscriber s;
	private Iterator<Integer> its;

	public MySubscription(Subscriber<? super Integer> s, Iterable<Integer> its) {
		this.s = s;
		this.its = its.iterator();
	}

	@Override
	public void request(long n) {
		while (n > 0) {
			if (its.hasNext()) {
				s.onNext(its.next());
			} else {
				s.onComplete();
				break;
			}
			n--;
		}
	}

	@Override
	public void cancel() {

	}
}
