package agg.samples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@EnableHystrix
@EnableCircuitBreaker
public class AggregateApp {

    public static void main(String[] args) {
        SpringApplication.run(AggregateApp.class, args);
    }
}
