package com.alvonellos.uptime.controller;

import com.alvonellos.uptime.dto.HostDTO;
import com.alvonellos.uptime.service.HostService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@WebMvcTest
public class HostControllerTests {

    private final List<HostDTO> hosts = List.of(
            new HostDTO(
                    UUID.randomUUID(),
                    "host1",
                    "192.168.1.1",
                    "00:00:00:00:00:01",
                    8080
            ),
            new HostDTO(
                    UUID.randomUUID(),
                    "host2",
                    "192.168.1.2",
                    "00:00:00:00:00:02",
                    8081
            )
    );

    @Autowired
    MockMvc mockMvc;

    @MockBean
    HostService hostService;

    @InjectMocks
    HostController hostController;

    @Before
    public void setUp() {
        assertNotNull(hostController);
        assertNotNull(hostService);
        assertNotNull(mockMvc);


        doReturn(hosts.get(0)).when(hostService).get(hosts.get(0).getId());
        doReturn(hosts.get(1)).when(hostService).get(hosts.get(1).getId());
    }

    @Test
    public void contextLoads() {
        assertNotNull(hostController);
        assertNotNull(hostService);
    }
}
