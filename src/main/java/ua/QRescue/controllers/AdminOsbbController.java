package ua.QRescue.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ua.QRescue.dto.OsbbDTO;
import ua.QRescue.models.Osbb;
import ua.QRescue.service.AdminOsbbService;
import ua.QRescue.util.ErrorResponse;
import ua.QRescue.util.MappingUtils;
import ua.QRescue.util.NotCreatedException;
import ua.QRescue.util.NotFoundException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/osbb/administrating")
public class AdminOsbbController {
    private final AdminOsbbService osbbAdminService;
    private final MappingUtils mappingUtils;

    @Autowired
    public AdminOsbbController(AdminOsbbService osbbAdminService, MappingUtils mappingUtils) {
        this.osbbAdminService = osbbAdminService;
        this.mappingUtils = mappingUtils;
    }
    @GetMapping
    public List<OsbbDTO> getAllOsbb(){
        return osbbAdminService.findAll().stream().map(mappingUtils::convertToOsbbDTO).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public OsbbDTO getOsbb(@PathVariable("id") int id){
        return mappingUtils.convertToOsbbDTO(osbbAdminService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> save (@RequestBody @Valid OsbbDTO osbbDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors){
                errorMessage.append(error.getField())
                        .append(("-")).append(error.getDefaultMessage())
                        .append(";");

            }
            throw  new NotCreatedException(errorMessage.toString());
        }
        osbbAdminService.save(mappingUtils.convertToOsbb(osbbDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }
    //todo add dto
    @PatchMapping("/{id}")
    public Osbb updateOsbb(@PathVariable(value = "id") int id, @RequestBody Map<String,Object> fields){
        return osbbAdminService.updateOsbb(id,fields);
    }
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
