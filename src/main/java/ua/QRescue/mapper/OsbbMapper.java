package ua.QRescue.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.QRescue.dto.OsbbDTO;
import ua.QRescue.models.Osbb;

@Mapper(componentModel = "spring")
public interface OsbbMapper {
    @Mapping(target = "login", source = "login")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "address", source = "address")
    Osbb mapToOsbb(OsbbDTO osbbDTO);

    @Mapping(target = "login", source = "login")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "address", source = "address")
    OsbbDTO mapToOsbbDTO(Osbb osbb);
}
