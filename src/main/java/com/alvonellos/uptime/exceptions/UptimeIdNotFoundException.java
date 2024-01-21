package com.alvonellos.uptime.exceptions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = true)
public class UptimeIdNotFoundException extends UptimeAPIException {
    private final UUID id;

    public UptimeIdNotFoundException(final UUID id) {
        super("Entity with id " + id + " not found");
        this.id = id;
    }
}
