package com.alvonellos.uptime.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HostDTO {

    @NotBlank(message = "Host name is required")
    @Size(min = 2, max = 50, message = "Host name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "IP address is required")
    @Pattern(regexp = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$", message = "Invalid IP address")
    private String ipAddress;

    @NotNull(message = "Port is required")
    @Min(value = 0, message = "Port must be a positive integer")
    @Max(value = 65535, message = "Port number cannot be greater than 65535")
    private Integer port;
}
