package com.transkript.reportcard.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public record UserDto(
        @JsonProperty(value = "id") Long id,
        @NotNull @JsonProperty(value = "email") String username,
        @JsonProperty(value = "first_name") String firstName,
        @JsonProperty(value = "last_name") String lastName,
        @JsonProperty(value = "phone") String phone,
        @JsonProperty(value = "address") String address
) implements Serializable { }
