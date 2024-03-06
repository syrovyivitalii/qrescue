package ua.QRescue.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.QRescue.dto.OsbbDTO;
import ua.QRescue.service.AdminOsbbServiceImpl;
import ua.QRescue.util.ErrorResponse;
import ua.QRescue.util.NotCreatedException;
import ua.QRescue.util.NotFoundException;
import ua.QRescue.util.UserAlreadyExists;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/osbb/admin")
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
    @GetMapping("/{login}")
    public ResponseEntity<OsbbDTO> getOsbb(@PathVariable("login") String login){
        var getOsbb = osbbAdminService.findOneByLogin(login);
        return ResponseEntity.ok(getOsbb);
    }

    @PostMapping
    public ResponseEntity<OsbbDTO> save (@RequestBody @Valid OsbbDTO osbbDTO, BindingResult bindingResult){
        var responseDto = osbbAdminService.save(osbbDTO,bindingResult);
        return ResponseEntity.ok(responseDto);
    }
    @PatchMapping("/{login}")
    public ResponseEntity<OsbbDTO> updateOsbb(@PathVariable(value = "login") String login, @RequestBody OsbbDTO osbbDTO){
        var responseDto = osbbAdminService.updateOsbb(login,osbbDTO);
        return ResponseEntity.ok(responseDto);
    }
    @Operation(description = "Delete osbb from db")
    @DeleteMapping("/{login}")
    public void deleteOsbb(@PathVariable(value = "login") String login){
        osbbAdminService.deleteOsbb(login);
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
    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException (UserAlreadyExists e){
        ErrorResponse response = new ErrorResponse(
                "User already exists",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
