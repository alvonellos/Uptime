package com.alvonellos.uptime.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UptimeAPIError {
    @JsonProperty("traceID")
    private final  String logRef;
    @JsonProperty("message")
    private final  String message;
    @JsonProperty("cause")
    private final  Exception cause;
}
