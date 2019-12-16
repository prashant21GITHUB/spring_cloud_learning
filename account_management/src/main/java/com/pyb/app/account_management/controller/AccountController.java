package com.pyb.app.account_management.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "account")
public class AccountController {

    @GetMapping
    protected String statusCheck() {
        return "AccountService- Running ";
    }
}
