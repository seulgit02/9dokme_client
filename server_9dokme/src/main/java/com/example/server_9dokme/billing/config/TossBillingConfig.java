package com.example.server_9dokme.billing.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class TossBillingConfig {

    @Value("${billing.toss.test_client_api_key}")
    private String testClientApiKey;

    @Value("${billing.toss.test_secret_api_key}")
    private String testSecretKey;

    @Value("${billing.toss.success_url}")
    private String successUrl;

    public String getTestSecretKey() {
        return testSecretKey;
    }

    @Value("${billing.toss.fail_url}")
    private String failUrl;

    public static final String BASE_URL = "https://api.tosspayments.com/v1";
    public static final String BILLING_AUTH_URL = BASE_URL + "/billing/authorizations/issue";
}

