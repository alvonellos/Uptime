package com.alvonellos.uptime.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class HostTests {


    @Test
    public void testHostEntity() {
        Host host = new Host(1L, "myhost", "192.168.1.1", 8080);
        assertEquals("myhost", host.getName());
        assertEquals("192.168.1.1", host.getIpAddress());
        assertEquals(Integer.valueOf(8080), host.getPort());
    }

}

