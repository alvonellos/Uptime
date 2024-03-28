package com.alvonellos.uptime.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class HostTests {


    @Test
    void testHostEntity() {
        Host host = new Host(UUID.randomUUID(), "myhost", "192.168.1.1", "00:00:00:00:00:00", 8080);
        assertEquals("myhost", host.getName());
        assertEquals("192.168.1.1", host.getIpAddress());
        assertEquals(Integer.valueOf(8080), host.getPort());
    }

}

