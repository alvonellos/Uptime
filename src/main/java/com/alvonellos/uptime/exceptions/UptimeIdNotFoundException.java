package com.alvonellos.uptime.exceptions;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = true)
public class UptimeIdNotFoundException extends UptimeAPIException {
    private final Long id;

    public UptimeIdNotFoundException(final Long id) {
        super("Entity with id " + id + " not found");
        this.id = id;
    }
}
