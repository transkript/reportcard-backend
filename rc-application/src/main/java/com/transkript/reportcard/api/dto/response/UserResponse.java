package com.transkript.reportcard.api.dto.response;

import java.io.Serializable;

public class UserResponse {
    public record Login (
            String token,
            String message
    ) implements Serializable { }
    public record Register (
            Long id,
            String username,
            String message
    ) implements Serializable {}
}
