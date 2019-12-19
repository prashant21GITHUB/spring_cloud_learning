package com.pyb.app.account_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "account")
public class AccountController {
	
	@Autowired
	private Environment env;
	
    @GetMapping
    protected String statusCheck() {
        return "AccountService- Running at " + env.getProperty("local.server.port");
    }
}
