package ua.QRescue.service;

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
        headOsbbRepository.save(headOsbb);
        dataRepository.save(data);

        return headOsbb;
    }

}
