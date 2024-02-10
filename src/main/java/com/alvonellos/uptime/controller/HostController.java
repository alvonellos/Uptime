package com.alvonellos.uptime.controller;

import com.alvonellos.uptime.dto.HostDTO;
import com.alvonellos.uptime.service.HostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/")
@Log
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HostController {
    private final HostService hostService;


    @GetMapping("hosts")
    public ResponseEntity<Page<HostDTO>> getAll() {
        return new ResponseEntity<>(hostService.getAll(PageRequest.of(0, (int) hostService.count())), HttpStatus.OK);
    }

    @GetMapping("host")
    public ResponseEntity<Page<HostDTO>> get(@RequestParam(name = "page", defaultValue = "0") Integer page,
                             @RequestParam(name = "size", defaultValue = "1") Integer size) {

        return ok(hostService.getAll(PageRequest.of(page, size)));


    }
    @GetMapping("host/{id}")
    public ResponseEntity<HostDTO> get(@PathVariable(name = "id") UUID id) {
        return ok(hostService.get(id));
    }

    @PostMapping("host")
    public ResponseEntity<UUID> post(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "ip") String ip,
            @RequestParam(name = "mac") String mac,
            @RequestParam(name = "port") Integer port
    ) {
        return new ResponseEntity<>(hostService.post(name, ip, mac, port), HttpStatus.CREATED);
    }

    @PutMapping("host")
    public ResponseEntity<HostDTO> update(
            @RequestParam(name = "id") UUID id,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "ip_address") String ip,
            @RequestParam(name = "mac") String mac,
            @RequestParam(name = "port") Integer port
    ) {
        return ok(hostService.update(id, name, ip, mac, port));
    }

    @DeleteMapping("host/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") UUID id) {
        hostService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "hosts", method = RequestMethod.HEAD)
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(hostService.count(), HttpStatus.OK);
    }
}
