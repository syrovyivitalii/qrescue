package ua.QRescue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.QRescue.models.HeadOsbb;
import ua.QRescue.service.HeadOsbbService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/osbb/head")
public class HeadOsbbController {

    private final HeadOsbbService headOsbbService;

    @Autowired
    public HeadOsbbController(HeadOsbbService headOsbbService) {
        this.headOsbbService = headOsbbService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<HeadOsbb> getHeadOsbb(@PathVariable int id) {
        HeadOsbb headOsbb = headOsbbService.getHeadOsbb(id);
        return ResponseEntity.ok(headOsbb);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<HeadOsbb> updateHeadOsbb(@PathVariable int id, @RequestBody HeadOsbb newHeadOsbb) {
        HeadOsbb updatedHeadOsbb = headOsbbService.updateHeadOsbb(id, newHeadOsbb);
        return ResponseEntity.ok(updatedHeadOsbb);
    }
}



//Добавити методи get...
//Добавити в хед сервісі бізнес логіку