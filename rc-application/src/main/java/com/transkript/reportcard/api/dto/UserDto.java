package com.transkript.reportcard.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public record UserDto(
        Long id,
        @NotNull String username,
        String firstName,
        String lastName,
        String phone, String address
) implements Serializable {
}
