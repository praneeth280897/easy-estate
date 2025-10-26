package com.easy.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CronJonScheduler {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String APP_URL = "https://easy-estate-1.onrender.com/";

    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void pingSelf() {
        try {
            restTemplate.getForObject(APP_URL, String.class);
            System.out.println("✅ Self-ping successful");
        } catch (Exception e) {
            System.out.println("⚠️ Self-ping failed: " + e.getMessage());
        }
    }
}
