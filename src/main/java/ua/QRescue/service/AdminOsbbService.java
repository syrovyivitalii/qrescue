package ua.QRescue.service;

import org.springframework.validation.BindingResult;
import ua.QRescue.dto.OsbbDTO;
import ua.QRescue.models.Osbb;

import java.util.List;
import java.util.Map;

public interface AdminOsbbService {
    List<OsbbDTO> findAll();
    OsbbDTO findOneByLogin(String login);
    OsbbDTO save(OsbbDTO osbbDTO, BindingResult bindingResult);
    OsbbDTO updateOsbb(String login, OsbbDTO osbbDTO);
    void deleteOsbb(String login);
}
