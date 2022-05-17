package com.transkript.reportcard.exception.body;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionBody {
    @JsonProperty("message")
    private String message;

    @JsonProperty("status")
    private HttpStatus status;

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();

    @JsonProperty("errors")
    private Map<String, String> errors;

    @JsonProperty("path")
    private String path;

    public static ExceptionBody buildExceptionBody(String message, WebRequest webRequest, HttpStatus httpStatus, Map<String, String> errors) {
        return ExceptionBody.builder().message(message).timestamp(LocalDateTime.now())
                .status(httpStatus).code(httpStatus.value()).errors(errors).path(webRequest.getContextPath())
                .build();
    }
}
