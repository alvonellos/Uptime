package com.alvonellos.uptime.dto;

import com.alvonellos.uptime.model.Host;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(name = "HostDTO", description = "Host DTO")
public class HostDTO {

    @JsonProperty("id")
    @Schema(description = "Host id")
    private UUID id;

    @JsonProperty("name")
    @NotBlank(message = "Host name is required")
    @Size(min = 2, max = 50, message = "Host name must be between 2 and 50 characters")
    @Schema(description = "Host name")
    private String name;

    @JsonProperty("ip")
    @NotBlank(message = "IP address is required")
    @Pattern(regexp = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$", message = "Invalid IP address")
    @Schema(description = "The IP address, ipv4")
    private String ipAddress;

    @JsonProperty("mac")
    @NotBlank(message = "MAC address is required in IEEE 802 Format ex: 00:00:00:00:00:00")
    @Pattern(regexp = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$")
    @Schema(description = "The MAC address, in the format of 00:00:00:00:00:00")
    private String macAddress;

    @JsonProperty("port")
    @NotNull(message = "Port is required")
    @Min(value = 0, message = "Port must be a positive integer")
    @Max(value = 65535, message = "Port number cannot be greater than 65535")
    @Schema(description = "The port number")
    private Integer port;

    public HostDTO(@NotNull Host host) {
        this.id = host.getId();
        this.macAddress = host.getMacAddress();
        this.port = host.getPort();
        this.ipAddress = host.getIpAddress();
    }
}
