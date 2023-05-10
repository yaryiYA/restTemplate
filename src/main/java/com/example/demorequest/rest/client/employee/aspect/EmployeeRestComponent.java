package com.example.demorequest.rest.client.employee.aspect;

import com.example.demorequest.rest.RestToken;
import com.example.demorequest.rest.client.employee.TokenClient;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;


@Component
public class EmployeeRestComponent {
    private final TokenClient tokenClient;
    RestToken restToken;

    public RestToken getRestToken() {
        return restToken.clone();
    }

    @Autowired
    public EmployeeRestComponent(TokenClient tokenClient) {
        this.tokenClient = tokenClient;
        this.restToken = new RestToken();
    }


    public HttpHeaders getEmployeeHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(restToken.getToken());
        return headers;
    }

}
