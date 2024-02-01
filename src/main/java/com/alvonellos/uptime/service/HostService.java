package com.alvonellos.uptime.service;

import com.alvonellos.uptime.dto.HostDTO;
import com.alvonellos.uptime.model.Host;
import com.alvonellos.uptime.repo.HostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Log
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HostService {
    private final HostRepository hostRepository;

    public long count() {
        return hostRepository.count();
    }

    public Page<HostDTO> getAll(Pageable pageable) {
        return hostRepository
              .findAll(pageable)
                .map(HostDTO::new);
    }
    public HostDTO get(UUID id) {
        return new HostDTO(hostRepository
                .getReferenceById(id));
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

    public HostDTO update(UUID id, String name, String ip, String mac, Integer port) {
        Host host = hostRepository.findById(id).orElseThrow();
        host.setName(name);
        host.setIpAddress(ip);
        host.setMacAddress(mac);
        host.setPort(port);
        return host.toDTO();
    }

    public void delete(UUID id) {
        hostRepository.deleteById(id);
    }

    public List<HostDTO> search(HostDTO example) {
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
                }).stream().map(HostDTO::new).toList();
    }
}
