package agg.samples.controller;

import agg.samples.commands.SimpleControlledFailCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Supplier;

@RestController
public class LoadCommandController {

    @RequestMapping("/load")
    public String loadHystrixCommand(
            @RequestParam(value = "count", defaultValue = "10", required = false) int repeatCount,
            @RequestParam(value = "failureRate", defaultValue = "0", required = false) double failureRate) {
        repeatTimes(repeatCount, () -> new SimpleControlledFailCommand(failureRate, "Hello"), "Hello");
        return "Done";
    }

    /**
     * Run Command n times, return the fraction of the time when it went through cleanly.
     *
     * @param n
     * @param commandSupplier
     * @return fraction of the instances where it went through cleanly.
     */
    private double repeatTimes(int n, Supplier<SimpleControlledFailCommand> commandSupplier, String expected) {
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
