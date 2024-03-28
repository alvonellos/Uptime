package com.alvonellos.uptime.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
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
    public void testHostDTOValidationSuccess() {
        Set<ConstraintViolation<HostDTO>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(hostDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testHostDTOValidationFailure() {

        Set<ConstraintViolation<HostDTO>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(invalidDTO);

        violations.forEach(v -> System.out.println(v.getMessage()));

        assertEquals(4, violations.size());
        // 1) port > 0; 2). 2 <= |name.length| <= 50;3). ip regex; 4). hostname required
    }
}
