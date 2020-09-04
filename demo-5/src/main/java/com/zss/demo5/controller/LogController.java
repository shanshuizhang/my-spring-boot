package com.zss.demo5.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
@Slf4j
public class LogController {

    @GetMapping("/info")
    public void info(){
        log.info("info");
    }

    @GetMapping("/debug")
    public void debug(){
        log.debug("debug");
    }

    @GetMapping("/error")
    public void error(){
        log.error("error");
    }
}
