package com.transkript.reportcard.api.dto.response;

import java.io.Serializable;

public class UserResponse {
    public record Auth(
            String sessionId,
            String message
    ) implements Serializable {
    }

    public record Register(
            Long id,
            String username,
            String message
    ) implements Serializable {
    }
}
