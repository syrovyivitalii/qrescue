package ua.QRescue.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.QRescue.models.HeadOsbb;
import ua.QRescue.repositories.HeadOsbbRepository;
import ua.QRescue.util.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class HeadOsbbService {
    private final HeadOsbbRepository headOsbbRepository;

    public HeadOsbbService(HeadOsbbRepository headOsbbRepository) {
        this.headOsbbRepository = headOsbbRepository;
    }

    public List<HeadOsbb> findAll(){
        return headOsbbRepository.findAll();
    }

    public HeadOsbb findOne(int id){
        Optional<HeadOsbb> foundHead = headOsbbRepository.findById(id);
        return foundHead.orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void save(HeadOsbb headOsbb){
        headOsbbRepository.save(headOsbb);
    }
}
