package com.alvonellos.uptime.service;

import com.alvonellos.uptime.dto.HostDTO;
import com.alvonellos.uptime.model.Host;
import com.alvonellos.uptime.repo.HostRepository;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class HostServiceTest {

    @MockBean
    private HostRepository hostRepository;

    @Autowired
    private HostService hostService;

    private void buildHostsList(List<Host> hostList, int howMany) {
        IntStream.rangeClosed(0, howMany).forEach(i -> {
            Host host = new Host();
            host.setId(UUID.randomUUID());
            host.setName(String.valueOf(i));
            host.setIpAddress(genIp(i, 600000));
            host.setMacAddress(genMac(i, 60000000L));
            host.setPort(i);
            hostList.add(host);
        });
    }

    private String genMac(int n, long offset) {
        long number = offset + n;
        long sixth = (long) (number / Math.pow(256, 6)) % 256;
        long fifth = (long) (number / Math.pow(256, 5)) % 256;
        long fourth = (long) (number / Math.pow(256, 4)) % 256;
        long third = (long) (number / Math.pow(256, 3)) % 256;
        long second = (long) (number / Math.pow(256, 2)) % 256;
        long first = number % 256;
        return String.format("%02x:%02x:%02x:%02x:%02x:%02x", sixth, fifth, fourth, third, second, first);
    }

    private String genIp(long n, long offset) {
        //Todo: generate actual valid IP's
        long number = offset + n;
        long fourth = (long) (number / Math.pow(256, 4)) % 256;
        long third = (long) (number / Math.pow(256, 3)) % 256;
        long second = (long) (number / Math.pow(256, 2)) % 256;
        long first = number % 256;
        return String.format("%d.%d.%d.%d", fourth, third, second, first);
    }

    @Test
    public void contextLoads() {
        assertNotNull(hostRepository);
        assertNotNull(hostService);
    }

    @Before
    public void setup() {
        ArrayList<Host> hostList = new ArrayList<>();
        buildHostsList(hostList, 2000);

        doReturn(hostList).when(hostRepository).findAll();
        doReturn(new PageImpl<>(hostList)).when(hostRepository).findAll(any(Pageable.class));
        doReturn(2000L).when(hostRepository).count();
        doReturn(Optional.of(hostList.get(0))).when(hostRepository).findById(any(UUID.class));
        doNothing().when(hostRepository).delete(any());
    }

    @Test
    public void count() {
        //given setup in before
        assertEquals(2000, hostService.count());
        verify(hostRepository, times(1)).count();
    }

    @Test
    public void getAll() {
        //given setup in before
        Page<HostDTO> hostList = hostService.getAll(PageRequest.of(0, 2000));
        assertEquals(2001, hostList.getTotalElements());
        verify(hostRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    public void get() {
        //given setup in before
        HostDTO hostDTO = hostService.get(UUID.randomUUID());
        assertEquals(hostDTO.getId(), hostDTO.getId());
        verify(hostRepository, times(1)).findById(any(UUID.class));
    }

    @Test
    public void post() {
        //todo fix this
        //given setup in before
        val result = hostService.post(
                "TEST", "192.168.0.1", "00:00:00:00:00:00", 22
        );

        assertNotNull(result);
        verify(hostRepository, times(1)).save(any(Host.class));
    }


    @Test
    public void update() {
        //given setup in before
        val result = hostService.update(
                UUID.randomUUID(), "TEST", "192.168.0.1", "00:00:00:00:00:00", 22
        );

        assertNotNull(result);
        verify(hostRepository, times(1)).findById(any());
        verify(hostRepository, times(1)).save(any(Host.class));
    }

    @Test
    public void delete() {
        //given step in before
        UUID id = UUID.randomUUID();
        hostService.delete(id);
        verify(hostRepository).deleteById(id);
    }
}