package ua.QRescue.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/osbb/head")
public class HeadOsbbController {
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_ADMIN')")
    public String get(){
        return "Page for head osbb";
    }
}
