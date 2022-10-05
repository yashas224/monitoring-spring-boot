package com.example.monitoringtrial;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DemoMetricsScheduler {

  private final DemoMetrics demoMetrics;

  public DemoMetricsScheduler(DemoMetrics demoMetrics) {
    this.demoMetrics = demoMetrics;
  }

  @Scheduled(fixedRate = 1000)
  public void triggerCustomMetrics() {
    demoMetrics.getRandomMetricsData();
  }

  @Scheduled(fixedRate = 2000)
  public void triggerCustomTimerMetrics() throws InterruptedException {
    demoMetrics.testTimerByInducingDelay();
  }
}