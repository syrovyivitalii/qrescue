package ua.QRescue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.QRescue.dto.HeadOsbbDataDTO;
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
    public ResponseEntity<HeadOsbbDataDTO> getHeadOsbb(@PathVariable int id) {
        HeadOsbbDataDTO headOsbbData = headOsbbService.getHeadOsbbData(id);
        return ResponseEntity.ok(headOsbbData);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<HeadOsbb> updateHeadOsbb(@PathVariable int id, @RequestBody HeadOsbbDataDTO dto) {
        HeadOsbb updatedHeadOsbb = headOsbbService.updateHeadOsbb(id, dto);
        return ResponseEntity.ok(updatedHeadOsbb);
    }
}



//Добавити методи get...
//Добавити в хед сервісі бізнес логіку