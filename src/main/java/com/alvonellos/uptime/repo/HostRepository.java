package com.alvonellos.uptime.repo;

import com.alvonellos.uptime.exceptions.UptimeIdNotFoundException;
import com.alvonellos.uptime.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HostRepository extends JpaRepository<Host, UUID> {
    Optional<Host> findById(UUID id);
}
