package com.dbogo.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  project
 */
@RestController("notice")
public class NoticeController {

    @GetMapping("/list")
    public String list() {
      return "Hello World";
    }

}
