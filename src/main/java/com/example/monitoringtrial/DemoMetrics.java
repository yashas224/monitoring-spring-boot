package com.example.monitoringtrial;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class DemoMetrics {
  private final Counter demoCounter;
  private final AtomicInteger demoGauge;

  public DemoMetrics(MeterRegistry meterRegistry) {
    this.demoCounter = meterRegistry.counter("demo.counter");
    this.demoGauge = meterRegistry.gauge("demo.gauge", new AtomicInteger(0));
  }

  public void getRandomMetricsData() {
    demoGauge.set(getRandomNumberInRange(0, 100));
    demoCounter.increment();
  }

  private static int getRandomNumberInRange(int min, int max) {
    if(min >= max) {
      throw new IllegalArgumentException("max must be greater than min");
    }

    Random r = new Random();
    return r.nextInt((max - min) + 1) + min;
  }

  @Timed(value = "test.timer", description = "Time spent in the test method")
  public void testTimerByInducingDelay() throws InterruptedException {
    Thread.sleep(new Random().nextInt(1500));
  }
}