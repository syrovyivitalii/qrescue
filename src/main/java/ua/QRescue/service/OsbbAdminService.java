package ua.QRescue.service;

import org.springframework.stereotype.Service;
import ua.QRescue.models.Osbb;
import ua.QRescue.repositories.OsbbRepository;
import ua.QRescue.util.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class OsbbAdminService {
    private final OsbbRepository osbbRepository;

    public OsbbAdminService(OsbbRepository osbbRepository) {
        this.osbbRepository = osbbRepository;
    }
    public List<Osbb> findAll(){
        return osbbRepository.findAll();
    }
    public Osbb findOne(int id){
        Optional<Osbb> foundOsbb = osbbRepository.findById(id);
        return foundOsbb.orElseThrow(NotFoundException::new);
    }
}
