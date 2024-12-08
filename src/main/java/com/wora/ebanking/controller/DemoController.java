package com.wora.ebanking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DemoController {

    @GetMapping("notices")
    public String getNotices() {
        return "here are the notice";
    }

    @GetMapping("contact")
    public String getContact(){
        return "here is our contact";
    }

    @GetMapping("my-loans")
    public String getMyLoans() {
        return "get my loans";
    }

    @GetMapping("my-card")
    public String getMyCards() {
        return "here are my cards";
    }

    @GetMapping("my-account")
    public String getAccount() {
        return "here are the accounts";
    }

    @GetMapping("my-balance")
    public String getBalance() {
        return "here is you balance";
    }
}
