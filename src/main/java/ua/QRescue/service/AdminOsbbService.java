package ua.QRescue.service;

import org.springframework.validation.BindingResult;
import ua.QRescue.dto.OsbbDTO;
import ua.QRescue.models.Osbb;

import java.util.List;
import java.util.Map;

public interface AdminOsbbService {
    List<OsbbDTO> findAll();
    OsbbDTO findOne(int id);
    OsbbDTO save(OsbbDTO osbbDTO, BindingResult bindingResult);
    OsbbDTO updateOsbb(int id, OsbbDTO osbbDTO);
    void deleteOsbb(int id);
}
