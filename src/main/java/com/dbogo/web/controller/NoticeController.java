package com.dbogo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * project
 */
@Controller
@RequestMapping("/notice/")
public class NoticeController {

    @GetMapping("/list")
    public String list() {
        return "Hello World";
    }

}
