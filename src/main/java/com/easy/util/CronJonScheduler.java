package com.easy.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class CronJonScheduler {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String APP_URL = "https://easy-estate-1.onrender.com/actuator/health";

    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void pingSelf() {
        try {
            restTemplate.getForObject(APP_URL, String.class);
            log.info("Self-ping successful");
        } catch (Exception e) {
            log.warn("Self-ping failed: " + e.getMessage());
        }
    }
}
