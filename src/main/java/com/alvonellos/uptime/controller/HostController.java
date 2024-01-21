package com.alvonellos.uptime.controller;

import com.alvonellos.uptime.dto.HostDTO;
import com.alvonellos.uptime.service.HostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/host")
@Log
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HostController {
    private final HostService hostService;
    @GetMapping
    public ResponseEntity<HostDTO> get(@RequestParam(name = "id") UUID id) {
        return ok(new HostDTO(hostService.get(id)));
    }

    @PostMapping
    public ResponseEntity<UUID> post(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "ip") String ip,
            @RequestParam(name = "mac") String mac,
            @RequestParam(name = "port") Integer port
    ) {
        return new ResponseEntity<>(hostService.post(name, ip, mac, port), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<HostDTO> update(
            @RequestParam(name = "id") UUID id,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "ip_address") String ip,
            @RequestParam(name = "mac") String mac,
            @RequestParam(name = "port") Integer port
    ) {
        return ok(new HostDTO(hostService.update(id, name, ip, mac, port)));
    }
}
