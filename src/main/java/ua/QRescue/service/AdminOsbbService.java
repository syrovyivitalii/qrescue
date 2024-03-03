package ua.QRescue.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import ua.QRescue.dto.OsbbDTO;
import ua.QRescue.mapper.OsbbMapper;
import ua.QRescue.models.Osbb;
import ua.QRescue.repositories.OsbbRepository;
import ua.QRescue.util.NotFoundException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AdminOsbbService {
    private final OsbbRepository osbbRepository;
    private final OsbbMapper osbbMapper;
    @Autowired
    public AdminOsbbService(OsbbRepository osbbRepository, OsbbMapper osbbMapper) {
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
    public void save(Osbb osbb){
        osbbRepository.save(osbb);
    }
    @Transactional
    public Osbb updateOsbb(int id, Map<String, Object> fields){
        Optional<Osbb> existingOsbb = osbbRepository.findById(id);
        if (existingOsbb.isPresent()){
            fields.forEach((key,value)->{
                Field field = ReflectionUtils.findField(Osbb.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field,existingOsbb.get(),value);
            });
            return osbbRepository.save(existingOsbb.get());
        }else {
            throw new NotFoundException();
        }
    }
    @Transactional
    public void deleteOsbb(int id){
        Osbb osbb = osbbRepository.findById(id).orElseThrow(NotFoundException::new);
        osbbRepository.deleteById(id);
    }
}
