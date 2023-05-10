package com.example.demorequest.rest.client.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpResponse;


@Service
public class TokenClient extends BaseClient {
    protected final RestTemplate restTemplate;
    protected final ApplicationContext applicationContext;
    @Value("${baseUrl}${tokenUrn}")
    protected String tokenEndPoint;
    @Value("${userEmp}")
    protected String userName;
    @Value("${userPassword}")
    protected String userPassword;

    @Autowired
    public TokenClient(RestTemplateBuilder restTemplateBuilder, ApplicationContext applicationContext) {
        this.restTemplate = restTemplateBuilder.build();
        this.applicationContext = applicationContext;
    }


    public ResponseEntity<String> getToken() {
        ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth(userName, userPassword);
            HttpEntity<?> entity = new HttpEntity<>(headers);
            response = restTemplate.exchange(tokenEndPoint, HttpMethod.POST, entity, String.class);
            return response;
        } catch (HttpClientErrorException exception) {
            return response;
        }


    }


}

