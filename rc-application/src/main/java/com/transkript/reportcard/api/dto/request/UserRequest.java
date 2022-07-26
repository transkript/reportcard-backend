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

    public record Register(
            @NotNull @JsonProperty(value = "username") String username,
            @NotNull @JsonProperty(value = "password") String password,
            @NotNull @JsonProperty(value = "first_name") String firstName,
            @NotNull @JsonProperty(value = "last_name") String lastName
    ) implements Serializable {
    }
}

