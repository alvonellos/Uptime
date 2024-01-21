package com.alvonellos.uptime.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class HostDTOTests {

    private static final HostDTO hostDTO =
            new HostDTO(UUID.randomUUID(),
                    "192.168.1.1",
                    "192.168.1.1",
                    "00:00:00:00:00:00",
                    8080
            );

    private static final HostDTO invalidDTO =
            new HostDTO(UUID.randomUUID(),
                    "",
                    "192.168.1.999",
                    "00:00:00:00:00",
                    0000000000
            );

    @Test
    void testHostDTOValidationSuccess() {
        Set<ConstraintViolation<HostDTO>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(hostDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testHostDTOValidationFailure() {

        Set<ConstraintViolation<HostDTO>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(invalidDTO);

        violations.forEach(v -> System.out.println(v.getMessage()));

        assertEquals(4, violations.size());
        // 1) port > 0; 2). 2 <= |name.length| <= 50;3). ip regex; 4). hostname required
    }
}
