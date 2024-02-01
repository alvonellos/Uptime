package com.alvonellos.uptime.scheduled;

import com.alvonellos.uptime.dto.HostDTO;
import com.alvonellos.uptime.model.Host;
import com.alvonellos.uptime.service.HostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.SocketException;
import java.util.stream.IntStream;

@Log
@Service
@RequiredArgsConstructor
public class ScheduledService {
    private final HostService hostService;
    @Scheduled(fixedRate = 10000)
    public void checkHosts() throws SocketException {
        final int pages = (int) (hostService.count() / 101);
        IntStream.rangeClosed(0, pages).forEach(page -> {
            final Page<HostDTO> hosts = hostService.getAll(PageRequest.of(page, 101));

        });

    }
}
