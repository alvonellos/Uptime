package com.alvonellos.uptime.service;

import com.alvonellos.uptime.dto.HostDTO;
import com.alvonellos.uptime.exceptions.UptimeIdNotFoundException;
import com.alvonellos.uptime.model.Host;
import com.alvonellos.uptime.repo.HostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Service
@Log
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HostService {
    private final HostRepository hostRepository;

    public Host get(UUID id) {
        return hostRepository
                .getReferenceById(id);
    }

    public UUID post(String name, String ip, String mac, Integer port) {
        Host host = new Host();
        host.setId(UUID.randomUUID());
        host.setName(name);
        host.setIpAddress(ip);
        host.setMacAddress(mac);
        host.setPort(port);
        return host.getId();
    }

    public Host update(UUID id, String name, String ip, String mac, Integer port) {
        Host host = get(id);
        host.setName(name);
        host.setIpAddress(ip);
        host.setMacAddress(mac);
        host.setPort(port);
        return host;
    }

    public void delete(UUID id) {
        Host host = get(id);
        hostRepository.delete(host);
    }

    public List<Host> search(HostDTO example) {
        return hostRepository
                .findAll(new Example<Host>() {
                    @Override
                    public Host getProbe() {
                        return new Host(example);
                    }

                    @Override
                    public ExampleMatcher getMatcher() {
                        return null;
                    }
                });
    }
}
