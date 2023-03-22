package com.alvonellos.uptime.service;

import com.alvonellos.uptime.repo.HostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class HostService {
    private final HostRepository hostRepository;

}
