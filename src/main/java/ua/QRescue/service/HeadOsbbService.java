package ua.QRescue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.QRescue.models.HeadOsbb;
import ua.QRescue.repositories.HeadOsbbRepository;
import ua.QRescue.util.NotFoundException;

//TODO: бізнес логіка:
@Service
public class HeadOsbbService {

    private final HeadOsbbRepository headOsbbRepository;

    @Autowired
    public HeadOsbbService(HeadOsbbRepository headOsbbRepository) {
        this.headOsbbRepository = headOsbbRepository;
    }

    public HeadOsbb getHeadOsbb(int id) {
        return headOsbbRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public HeadOsbb updateHeadOsbb(int id, HeadOsbb newHeadOsbb) {
        return headOsbbRepository.findById(id)
                .map(headOsbb -> {
                    headOsbb.setName(newHeadOsbb.getName());
 //                   headOsbb.setSurname(newHeadOsbb.getSurname());
                    headOsbb.setPhone(newHeadOsbb.getPhone());
                    headOsbb.setEmail(newHeadOsbb.getEmail());
   //                 headOsbb.setAddress(newHeadOsbb.getAddress());
                    // ...update other fields as needed
                    return headOsbbRepository.save(headOsbb);
                })
                .orElseThrow(NotFoundException::new);
    }

}
