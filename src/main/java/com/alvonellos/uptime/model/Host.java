package com.alvonellos.uptime.model;

import com.alvonellos.uptime.dto.HostDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "hosts")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Host {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Host name is required")
    @Size(min = 2, max = 50, message = "Host name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "IP address is required")
    @Pattern(regexp = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$", message = "Invalid IP address")
    private String ipAddress;

    @NotBlank(message = "MAC address is required in IEEE 802 Format ex: 00:00:00:00:00:00")
    @Pattern(regexp = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$")
    private String macAddress;

    @NotNull(message = "Port is required")
    @Min(value = 0, message = "Port must be a positive integer")
    @Max(value = 65535, message = "Port number cannot be greater than 65535")
    private Integer port;

    public Host(HostDTO hostDTO) {
        this.id = hostDTO.getId();
        this.name = hostDTO.getName();
        this.ipAddress = hostDTO.getIpAddress();
        this.macAddress = hostDTO.getMacAddress();
        this.port = hostDTO.getPort();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Host host = (Host) o;
        return getId() != null && Objects.equals(getId(), host.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public HostDTO  toDTO() {
        return new HostDTO(this);
    }
}
