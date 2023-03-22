package com.alvonellos.uptime.exceptions;

public class UptimeIllegalArgumentException extends UptimeAPIException {
    public UptimeIllegalArgumentException(String message) {
        super(message);
    }

    public UptimeIllegalArgumentException(String message, Exception cause) {
        super(message, cause);
    }
}
