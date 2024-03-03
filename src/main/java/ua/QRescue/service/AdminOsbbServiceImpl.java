package ua.QRescue.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ua.QRescue.dto.OsbbDTO;
import ua.QRescue.mapper.OsbbMapper;
import ua.QRescue.models.Osbb;
import ua.QRescue.repositories.OsbbRepository;
import ua.QRescue.util.NotCreatedException;
import ua.QRescue.util.NotFoundException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AdminOsbbServiceImpl implements AdminOsbbService{
    private final OsbbRepository osbbRepository;
    private final OsbbMapper osbbMapper;
    @Autowired
    public AdminOsbbServiceImpl(OsbbRepository osbbRepository, OsbbMapper osbbMapper) {
        this.osbbRepository = osbbRepository;
        this.osbbMapper = osbbMapper;
    }


    public List<OsbbDTO> findAll(){
        var allOsbb = osbbRepository.findAll();
        return allOsbb.stream().map(osbbMapper::mapToOsbbDTO).collect(Collectors.toList());
    }
    public OsbbDTO findOne(int id){
        var osbbById = osbbRepository.findById(id).orElseThrow(NotFoundException::new);
        return osbbMapper.mapToOsbbDTO(osbbById);
    }
    @Transactional
    public OsbbDTO save(OsbbDTO osbbDTO, BindingResult bindingResult){
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
        var osbb = osbbMapper.mapToOsbb(osbbDTO);
        var savedOsbb = osbbRepository.save(osbb);
        return osbbMapper.mapToOsbbDTO(savedOsbb);
    }
    @Transactional
    public OsbbDTO updateOsbb(int id, OsbbDTO osbbDTO){
        var osbbById = osbbRepository.findById(id).orElseThrow(NotFoundException::new);
        osbbMapper.patchMerge(osbbDTO, osbbById);
        var putOsbb = osbbRepository.save(osbbById);
        return osbbMapper.mapToOsbbDTO(putOsbb);
    }
    @Transactional
    public void deleteOsbb(int id){
        osbbRepository.deleteById(id);
    }
}
