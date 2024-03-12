package ua.QRescue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        if (headOsbbData != null) {
            return ResponseEntity.ok(headOsbbData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<HeadOsbb> createHeadOsbb(@RequestBody HeadOsbbDataDTO dto) {
        try {
            HeadOsbb createdHeadOsbb = headOsbbService.createHeadOsbb(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdHeadOsbb);
        } catch (Exception e) {
            // Log the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<HeadOsbb> updateHeadOsbb(@PathVariable int id, @RequestBody HeadOsbbDataDTO dto) {
        try {
            HeadOsbb updatedHeadOsbb = headOsbbService.updateHeadOsbb(id, dto);
            if (updatedHeadOsbb != null) {
                return ResponseEntity.ok(updatedHeadOsbb);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
