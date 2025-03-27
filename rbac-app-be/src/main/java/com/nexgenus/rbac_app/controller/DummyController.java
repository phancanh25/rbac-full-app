package com.nexgenus.rbac_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {

    // Accessible only to users with the USER role
    @GetMapping("/api/v1/user")
    public String userAccess() {
        return "Hello User!";
    }

    // Accessible only to users with the ADMIN role
    @GetMapping("/api/v1/admin")
    public String adminAccess() {
        return "Hello Admin!";
    }

    // Accessible only to users with the ADMIN role
    @GetMapping("/api/v1/common")
    public String commonAccess() {
        return "Hello all!";
    }
}
