package com.alvonellos.uptime.controller;

import com.alvonellos.uptime.dto.HostDTO;
import com.alvonellos.uptime.service.HostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    HostService hostService;

    @InjectMocks
    HostController hostController;

    @Before
    public void setUp() {
        assertNotNull(hostController);
        assertNotNull(hostService);
        assertNotNull(mockMvc);

        doReturn(2L).when(hostService).count();
        doReturn(new PageImpl<>(hosts)).when(hostService).getAll(PageRequest.of(0, 1));
        doReturn(new PageImpl<>(List.of(hosts.get(0)))).when(hostService).getAll(PageRequest.of(0, 1));
        doReturn(new PageImpl<>(List.of(hosts.get(1)))).when(hostService).getAll(PageRequest.of(1, 1));
        doReturn(hosts.get(0)).when(hostService).get(hosts.get(0).getId());
        doReturn(hosts.get(1)).when(hostService).get(hosts.get(1).getId());
    }

//    @Test
//    public void getAll() throws Exception { //TODO: FIX
//        mockMvc.perform(get("/api/hosts"))
//             .andExpect(status().isOk())
//             .andExpect(content().string(objectMapper.writeValueAsString(
//                     new PageImpl<>(hosts)
//             )));
//    }

    @Test
    public void getOne() throws Exception {
        mockMvc.perform(get("/api/host/" + hosts.get(0).getId()))
           .andExpect(status().isOk())
           .andExpect(content().string(objectMapper.writeValueAsString(hosts.get(0))));
    }

//    @Test
//    public void getTwoPages() throws Exception { //TODO: FIX
//        val page1 = mockMvc.perform(get("/api/hosts?page=0&size=1"));
//        val page2 = mockMvc.perform(get("/api/hosts?page=1&size=1"));
//
//        page1.andExpect(status().isOk());
//        page2.andExpect(status().isOk());
//        page1.andExpect(content().string(objectMapper.writeValueAsString(
//                new PageImpl<>(List.of(hosts.get(0)))
//        )));
//
//        page2.andExpect(content().string(objectMapper.writeValueAsString(
//                new PageImpl<>(List.of(hosts.get(1))))
//        ));
//
//    }

    @Test
    public void postTest() throws Exception {
        mockMvc.perform(post("/api/host")
                .param("name", "host1")
                .param("ip", "192.168.0.7")
                .param("mac", "00:00:00:00:00:01")
                .param("port", "8080"))
             .andExpect(status().isCreated());
    }

    @Test
    public void putTest() throws Exception {
        mockMvc.perform(put("/api/host")
               .param("id", String.valueOf(hosts.get(0).getId()))
               .param("name", "host2")
               .param("ip_address", "192.168.0.7")
               .param("mac", "00:00:00:00:00:01")
               .param("port", "8080"))
            .andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(delete("/api/host/" + hosts.get(0).getId()))
           .andExpect(status().isNoContent());
    }

    @Test
    public void headTest() throws Exception {
        mockMvc.perform(head("/api/hosts"))
         .andExpect(status().isOk());
    }

    @Test
    public void contextLoads() {
        assertNotNull(hostController);
        assertNotNull(hostService);
    }
}
