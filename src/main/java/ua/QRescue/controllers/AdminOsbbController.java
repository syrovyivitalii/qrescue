package ua.QRescue.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.QRescue.dto.OsbbDTO;
import ua.QRescue.models.Osbb;
import ua.QRescue.service.OsbbAdminService;
import ua.QRescue.util.ErrorResponse;
import ua.QRescue.util.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/osbb/administrating")
public class AdminOsbbController {
    private final OsbbAdminService osbbAdminService;
    private final ModelMapper modelMapper;

    @Autowired
    public AdminOsbbController(OsbbAdminService osbbAdminService, ModelMapper modelMapper) {
        this.osbbAdminService = osbbAdminService;
        this.modelMapper = modelMapper;
    }
    @GetMapping
    public List<OsbbDTO> getAllOsbb(){
        return osbbAdminService.findAll().stream().map(this::convertToOsbbDTO).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public OsbbDTO getOsbb(@PathVariable("id") int id){
        return convertToOsbbDTO(osbbAdminService.findOne(id));
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException (NotFoundException e){
        ErrorResponse response = new ErrorResponse(
                "This id was not found!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private OsbbDTO convertToOsbbDTO(Osbb osbb){
        return modelMapper.map(osbb, OsbbDTO.class);
    }
    private Osbb convertToOsbb(OsbbDTO osbbDTO){
        return modelMapper.map(osbbDTO,Osbb.class);
    }

}
