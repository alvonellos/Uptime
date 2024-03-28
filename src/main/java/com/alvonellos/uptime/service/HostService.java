package com.alvonellos.uptime.service;

import com.alvonellos.uptime.dto.HostDTO;
import com.alvonellos.uptime.model.Host;
import com.alvonellos.uptime.repo.HostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
                .findById(id).orElseThrow());
    }

    public UUID post(String name, String ip, String mac, Integer port) {
        Host host = new Host();
        host.setId(UUID.randomUUID());
        host.setName(name);
        host.setIpAddress(ip);
        host.setMacAddress(mac);
        host.setPort(port);
        hostRepository.save(host);
        return host.getId();
    }

    public HostDTO update(UUID id, String name, String ip, String mac, Integer port) {
        Host host = hostRepository.findById(id).orElseThrow();
        host.setName(name);
        host.setIpAddress(ip);
        host.setMacAddress(mac);
        host.setPort(port);
        hostRepository.save(host);
        return host.toDTO();
    }

    public void delete(UUID id) {
        hostRepository.deleteById(id);
    }
}
