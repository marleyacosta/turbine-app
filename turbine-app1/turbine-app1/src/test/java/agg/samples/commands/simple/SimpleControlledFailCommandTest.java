package agg.samples.commands.simple;

import agg.samples.commands.SimpleControlledFailCommand;
import org.junit.Test;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.*;

public class SimpleControlledFailCommandTest {

    @Test
    public void noFailRateShouldResultInNoFailures() {
        double successPercent = repeatTimes(100, () -> new SimpleControlledFailCommand(0, "Hello"), "Hello");
        System.out.println(successPercent);
        assertThat(successPercent).isEqualTo(1.0);
    }

    @Test
    public void tenPercentFailRateShouldResultIn90PercentSuccess() {
        double successPercent = repeatTimes(100, () -> new SimpleControlledFailCommand(0.1, "Hello"), "Hello");
        System.out.println(successPercent);
        assertThat(successPercent).isBetween(0.8, 0.95);
    }




    /**
     * Run Command n times, return the fraction of the time when it went through cleanly.
     *
     * @param n
     * @param commandSupplier
     * @return fraction of the instances where it went through cleanly.
     */
    public double repeatTimes(int n, Supplier<SimpleControlledFailCommand> commandSupplier, String expected) {
        int total = 0;
        int success = 0;
        for (int i = 0; i < n; i++) {
            String response = commandSupplier.get().execute();
            total += 1;
            if (response.equals(expected)) {
                success += 1;
            }
        }
        return (success * 1.0) / total;
    }

}