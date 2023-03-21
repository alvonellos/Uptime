package com.alvonellos.uptime.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class HostDTOTests {

    @Test
    void testHostDTOValidationSuccess() {
        HostDTO hostDTO = new HostDTO("myhost", "192.168.1.1", 8080);
        Set<ConstraintViolation<HostDTO>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(hostDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testHostDTOValidationFailure() {
        HostDTO hostDTO = new HostDTO("", "invalid_ip", -1);
        Set<ConstraintViolation<HostDTO>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(hostDTO);

        violations.forEach(v -> System.out.println(v.getMessage()));

        assertFalse(violations.isEmpty());
        assertEquals(4, violations.size());
        // 1) port > 0; 2). 2 <= |name.length| <= 50;3). ip regex; 4). hostname required
    }
}
