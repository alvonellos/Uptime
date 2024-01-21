package com.alvonellos.uptime.scheduled;

import com.alvonellos.uptime.service.HostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.SocketException;

@Log
@Service
@RequiredArgsConstructor
public class ScheduledService {
    private final HostService hostService;
    @Scheduled(fixedRate = 10)
    public void checkHosts() throws SocketException {
    //discover all hosts on local network

    }
}
