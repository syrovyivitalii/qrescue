package ua.QRescue.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.QRescue.dto.OsbbDTO;
import ua.QRescue.models.Osbb;
import ua.QRescue.service.AdminOsbbServiceImpl;
import ua.QRescue.util.ErrorResponse;
import ua.QRescue.util.NotCreatedException;
import ua.QRescue.util.NotFoundException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/osbb/administrating")
public class AdminOsbbController {
    private final AdminOsbbServiceImpl osbbAdminService;

    @Autowired
    public AdminOsbbController(AdminOsbbServiceImpl osbbAdminService) {
        this.osbbAdminService = osbbAdminService;
    }
    @GetMapping
    public ResponseEntity<List<OsbbDTO>> getAllOsbb(){
        var allOsbb = osbbAdminService.findAll();
        return ResponseEntity.ok(allOsbb);
    }
    @GetMapping("/{id}")
    public ResponseEntity<OsbbDTO> getOsbb(@PathVariable("id") int id){
        var getOsbb = osbbAdminService.findOne(id);
        return ResponseEntity.ok(getOsbb);
    }

    @PostMapping
    public ResponseEntity<OsbbDTO> save (@RequestBody @Valid OsbbDTO osbbDTO, BindingResult bindingResult){
        var responseDto = osbbAdminService.save(osbbDTO,bindingResult);
        return ResponseEntity.ok(responseDto);
    }
    //todo add dto
    @PatchMapping("/{id}")
    public ResponseEntity<OsbbDTO> updateOsbb(@PathVariable(value = "id") int id, @RequestBody OsbbDTO osbbDTO){
        var responseDto = osbbAdminService.updateOsbb(id,osbbDTO);
        return ResponseEntity.ok(responseDto);
    }
    @Operation(description = "Delete osbb from db")
    @DeleteMapping("/{id}")
    public void deleteOsbb(@PathVariable(value = "id") int id){
        osbbAdminService.deleteOsbb(id);
    }
    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException (NotFoundException e){
        ErrorResponse response = new ErrorResponse(
                "This id was not found!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException (NotCreatedException e){
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
