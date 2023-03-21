package com.alvonellos.uptime.repository;

import com.alvonellos.uptime.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostsRepository extends JpaRepository<Host, Long> {
}
