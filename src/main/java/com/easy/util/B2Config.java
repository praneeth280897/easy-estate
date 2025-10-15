package com.easy.util;

import com.backblaze.b2.client.B2StorageClient;
import com.backblaze.b2.client.B2StorageClientFactory;
import com.backblaze.b2.client.structures.B2Bucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class B2Config {

    @Value("${backblaze.accountId}")
    private String accountId;

    @Value("${backblaze.applicationKey}")
    private String applicationKey;

    @Value("${backblaze.bucketName}")
    private String bucketName;

    @Bean
    public B2StorageClient b2StorageClient() {
        log.info(accountId,":::::AccountId");
        log.info(applicationKey,":::applicationKey");
        log.info(bucketName);
        return B2StorageClientFactory
                .createDefaultFactory()
                .create(accountId, applicationKey, "springboot-app");
    }

    @Bean
    public B2Bucket b2Bucket(B2StorageClient client) throws Exception {
        return client.getBucketOrNullByName(bucketName);
    }
}
