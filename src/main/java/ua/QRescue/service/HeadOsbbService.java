package ua.QRescue.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.QRescue.dto.HeadOsbbDataDTO;
import ua.QRescue.models.Data;
import ua.QRescue.models.HeadOsbb;
import ua.QRescue.repositories.DataRepository;
import ua.QRescue.repositories.HeadOsbbRepository;
import ua.QRescue.util.NotFoundException;

//TODO: бізнес логіка:
@Service
//@Slf4j
public class HeadOsbbService {

    private final HeadOsbbRepository headOsbbRepository;
    private final DataRepository dataRepository;

    @Autowired
    public HeadOsbbService(HeadOsbbRepository headOsbbRepository, DataRepository dataRepository) {
        this.headOsbbRepository = headOsbbRepository;
        this.dataRepository = dataRepository;
    }

    public HeadOsbbDataDTO getHeadOsbbData(int id) {
        // Retrieve HeadOsbb entity
        HeadOsbb headOsbb = headOsbbRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        // Retrieve Data entity
        Data data = dataRepository.findById(id).orElse(new Data());

        // Create HeadOsbbDataDTO and populate fields
        HeadOsbbDataDTO headOsbbData = new HeadOsbbDataDTO();
        headOsbbData.setId(id);
        headOsbbData.setName(headOsbb.getName());
        headOsbbData.setLastname(headOsbb.getLastname());
        headOsbbData.setPhone(headOsbb.getPhone());
        headOsbbData.setEmail(headOsbb.getEmail());
        headOsbbData.setAddress(data.getAddress());
        headOsbbData.setNumResidents(data.getNumResidents());
        headOsbbData.setResidentsWithDisabilities(data.getResidentsWithDisabilities());
        headOsbbData.setEmergencyExits(data.getEmergencyExits());
        headOsbbData.setFireEquipment(data.getFireEquipment());
        headOsbbData.setGasSupply(data.getGasSupply());
        headOsbbData.setElectricitySupply(data.getElectricitySupply());
        headOsbbData.setWaterSupply(data.getWaterSupply());

        return headOsbbData;
    }

    @Transactional
    public HeadOsbb updateHeadOsbb(int id, HeadOsbbDataDTO dto) {
        // Retrieve HeadOsbb entity
        HeadOsbb headOsbb = headOsbbRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        // Update HeadOsbb fields
        headOsbb.setName(dto.getName());
        headOsbb.setLastname(dto.getLastname());
        headOsbb.setPhone(dto.getPhone());
        headOsbb.setEmail(dto.getEmail());

        // Retrieve Data entity
        Data data = dataRepository.findById(id).orElse(new Data());

        // Update Data fields
        data.setAddress(dto.getAddress());
        data.setNumResidents(dto.getNumResidents());
        data.setResidentsWithDisabilities(dto.getResidentsWithDisabilities());
        data.setEmergencyExits(dto.getEmergencyExits());
        data.setFireEquipment(dto.getFireEquipment());
        data.setGasSupply(dto.getGasSupply());
        data.setElectricitySupply(dto.getElectricitySupply());
        data.setWaterSupply(dto.getWaterSupply());

        // Save entities
        try {

            headOsbbRepository.save(headOsbb);
            dataRepository.save(data);
        }catch (Exception e){
//            log.error(e.getMessage(),e);
            System.err.println(e.getMessage());
        }

        return headOsbb;
    }

    public HeadOsbb createHeadOsbb(HeadOsbbDataDTO dto) {
        // Create a new HeadOsbb entity
        HeadOsbb headOsbb = new HeadOsbb();
        headOsbb.setName(dto.getName());
        headOsbb.setLastname(dto.getLastname());
        headOsbb.setPhone(dto.getPhone());
        headOsbb.setEmail(dto.getEmail());

        // Save the new HeadOsbb entity
        HeadOsbb savedHeadOsbb = headOsbbRepository.save(headOsbb);

        // Create a new Data entity
        Data data = new Data();

        data.setId(savedHeadOsbb.getId()); // Set the ID of the associated HeadOsbb entity
        data.setAddress(dto.getAddress());
        data.setNumResidents(dto.getNumResidents());
        data.setResidentsWithDisabilities(dto.getResidentsWithDisabilities());
        data.setEmergencyExits(dto.getEmergencyExits());
        data.setFireEquipment(dto.getFireEquipment());
        data.setGasSupply(dto.getGasSupply());
        data.setElectricitySupply(dto.getElectricitySupply());
        data.setWaterSupply(dto.getWaterSupply());

        // Save the new Data entity
        dataRepository.save(data);

        return savedHeadOsbb;
    }

}
