package com.leandro.flow;

import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscription;
import java.util.List;
import java.util.LinkedList;
import com.google.common.flogger.FluentLogger;
import com.google.common.flogger.StackSize;

import lombok.Data;

public @Data class MessageSubscriber implements Flow.Subscriber<String> {
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();
	private final String name;
	private Subscription subscription;
    public List<String> consumedElements = new LinkedList<>();

	/**
     * called when the Publisher is closed
     */
	@Override
	public void onComplete() {
		logger.atInfo().log("%s says bye-bye.", name);
	}

	/**
	 * is called whenever some exception will be throw in the processing
	 */
	@Override
	public void onError(Throwable e) {
		logger.atSevere().withStackTrace(StackSize.FULL).withCause(e);
	}

	/**
	 * this is called whenever the Publisher publishes a new message
	 */
	@Override
	public void onNext(String message) {
		logger.atInfo().log("%s received message: %s.", name, message);
	    subscription.request(1);
	}

	/*
	 * The onSubscribe() method is called before processing starts. The instance of
	 * the Subscription is passed as the argument. It is a class that is used to
	 * control the flow of messages between Subscriber and the Publisher:
	 */
	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
        subscription.request(1);
	}
}