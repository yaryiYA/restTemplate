package com.example.demorequest.rest.client.employee.aspect;

import com.example.demorequest.rest.client.employee.TokenClient;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class EmployeeRestAspect {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected final TokenClient tokenClient;
    protected final EmployeeRestComponent employeeRestComponent;


    @Autowired
    public EmployeeRestAspect(TokenClient tokenClient, EmployeeRestComponent employeeRestComponent) {
        this.tokenClient = tokenClient;
        this.employeeRestComponent = employeeRestComponent;
    }

    @Pointcut("execution(public * com.example.demorequest.rest.client.employee.v1.EmployeeClient.*(..))")
    public void callToken() {
    }

    @Around(value = "callToken()", argNames = "pjp")
    public Object trowingToken(ProceedingJoinPoint pjp) {
        try {
            return pjp.proceed(pjp.getArgs());
        } catch (Throwable throwable) {

            ResponseEntity<String> token = tokenClient.getToken();
            if (token.getStatusCode().is2xxSuccessful()) {
                employeeRestComponent.restToken.setToken(token.getBody());
            } else {
                logger.error("login and password not valid");
                System.exit(0);
            }
            try {
                return pjp.proceed(pjp.getArgs());
            } catch (Throwable throwable1) {
                return trowingToken(pjp);
            }
        }
    }
}
