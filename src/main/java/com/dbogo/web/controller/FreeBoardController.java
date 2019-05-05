package com.dbogo.web.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * project
 */
@Controller
@RequestMapping("/freeBoard/")
@Log
public class FreeBoardController {

    @GetMapping("/list")
    public void list() {
        log.info("list() ... called");
    }
}