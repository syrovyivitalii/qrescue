package ua.QRescue.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import ua.QRescue.dto.OsbbDTO;
import ua.QRescue.models.Osbb;
import ua.QRescue.repositories.OsbbRepository;
import ua.QRescue.util.NotFoundException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AdminOsbbService {
    private final OsbbRepository osbbRepository;

    public AdminOsbbService(OsbbRepository osbbRepository) {
        this.osbbRepository = osbbRepository;
    }
    public List<Osbb> findAll(){
        return osbbRepository.findAll();
    }
    public Osbb findOne(int id){
        Optional<Osbb> foundOsbb = osbbRepository.findById(id);
        return foundOsbb.orElseThrow(NotFoundException::new);
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
