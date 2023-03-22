package com.alvonellos.uptime.exceptions;


public abstract class UptimeAPIException extends Exception {
    public UptimeAPIException(String message) {
        super(message);
    }

    public UptimeAPIException(String message, Exception cause) {
        super(message, cause);
    }
}
