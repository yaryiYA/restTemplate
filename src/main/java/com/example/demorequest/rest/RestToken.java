package com.example.demorequest.rest;

public class RestToken implements Cloneable{
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public RestToken clone() {
        try {

            return (RestToken) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
