package com.transkript.reportcard.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserRequest {
    public record Login(
            @NotNull @JsonProperty(value = "username") String username,
            @NotNull @JsonProperty(value = "password") String password
    ) implements Serializable {
    }

    public record Logout(
            @NotNull @JsonProperty String sessionId
    ) implements Serializable {
    }

    public record Register(
            @NotNull String username,
            @NotNull String password,
            @NotNull String firstName,
            @NotNull String lastName
    ) implements Serializable {
    }
}

