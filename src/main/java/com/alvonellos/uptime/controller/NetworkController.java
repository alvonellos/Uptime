package com.alvonellos.uptime.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Configure an entire network for monitoring
 */
@Log
@RestController
@RequestMapping("/network")
@RequiredArgsConstructor
public class NetworkController {
}
