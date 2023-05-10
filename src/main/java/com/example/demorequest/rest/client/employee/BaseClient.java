package com.example.demorequest.rest.client.employee;

import org.springframework.beans.factory.annotation.Value;

public abstract class BaseClient {
    @Value("${baseUrl}")
        protected String baseUrl;

}

