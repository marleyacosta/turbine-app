package agg.samples.commands;

import agg.samples.util.Util;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.Random;

/**
 * Will deliberately throw an exception a certain percent of the time.
 */
public class SimpleControlledFailCommand extends HystrixCommand<String> {

    private final double failureRate;
    private final String message;
    private final long delay;

    public SimpleControlledFailCommand(String message) {
        this(0.0, 0, message);
    }

    public SimpleControlledFailCommand(double failureRate, String message) {
        this(failureRate, 0, message);
    }

    public SimpleControlledFailCommand(double failureRate, long delay, String message) {
        super(HystrixCommandGroupKey.Factory.asKey("default"));
        if (failureRate > 1.0 || failureRate < 0.0) {
            throw new IllegalStateException("failure rate has to be between 0.0 and 1.0");
        }
        this.failureRate = failureRate;
        this.delay = delay;
        this.message = message;
    }

    @Override
    protected String run() {
        Random random = new Random();
        Util.delay(this.delay);
        if (random.nextDouble() <= this.failureRate) {
            throw new RuntimeException("Throwing a deliberate exception - failure rate condition matched");
        }
        return this.message;
    }

    @Override
    protected String getFallback() {
        return "fallback";
    }
}
