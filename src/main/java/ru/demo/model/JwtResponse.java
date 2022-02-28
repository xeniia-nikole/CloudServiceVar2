package ru.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    @JsonProperty("auth-token")
    private final String authToken;

    public JwtResponse(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }
}