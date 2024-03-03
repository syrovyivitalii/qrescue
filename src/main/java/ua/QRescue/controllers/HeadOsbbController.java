package ua.QRescue.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ua.QRescue.dto.HeadOsbbDTO;
import ua.QRescue.models.HeadOsbb;
import ua.QRescue.service.HeadOsbbService;
import ua.QRescue.util.ErrorResponse;
import ua.QRescue.util.NotCreatedException;
import ua.QRescue.util.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/headOsbb")
public class HeadOsbbController {
    private final HeadOsbbService headOsbbService;
    private final ModelMapper modelMapper;

    @Autowired
    public HeadOsbbController(HeadOsbbService headOsbbService, ModelMapper modelMapper) {
        this.headOsbbService = headOsbbService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<HeadOsbbDTO> getHeads (){
        return headOsbbService.findAll().stream().map(this::convertToHeadOsbbDTO).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public HeadOsbbDTO getHead(@PathVariable("id") int id){
        return convertToHeadOsbbDTO(headOsbbService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> save (@RequestBody @Valid HeadOsbbDTO headOsbbDTO, BindingResult bindingResult){
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
        headOsbbService.save(convertToHeadOsbb(headOsbbDTO));
        return ResponseEntity.ok(HttpStatus.OK);
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
    private HeadOsbbDTO convertToHeadOsbbDTO(HeadOsbb headOsbb){
        return modelMapper.map(headOsbb,HeadOsbbDTO.class);
    }
    private HeadOsbb convertToHeadOsbb(HeadOsbbDTO headOsbbDTO){
        return modelMapper.map(headOsbbDTO,HeadOsbb.class);
    }
}
