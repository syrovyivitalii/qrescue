package ua.QRescue.util;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ua.QRescue.dto.OsbbDTO;
import ua.QRescue.models.Osbb;

@Service
public class MappingUtils {
    private final ModelMapper modelMapper;

    public MappingUtils(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public OsbbDTO convertToOsbbDTO(Osbb osbb){
        return modelMapper.map(osbb, OsbbDTO.class);
    }
    public Osbb convertToOsbb(OsbbDTO osbbDTO){
        return modelMapper.map(osbbDTO,Osbb.class);
    }
}
