package com.example.AUTH.dto;

public class JwtTokenResponse {

    private String token;
    private String type;
    private String validUntil;

    public JwtTokenResponse() {
    }

    public JwtTokenResponse(String token, String type, String validUntil) {
        this.token = token;
        this.type = type;
        this.validUntil = validUntil;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(String validUntil) {
        this.validUntil = validUntil;
    }
}
