package ua.QRescue.mapper;

import org.mapstruct.*;
import ua.QRescue.dto.OsbbDTO;
import ua.QRescue.models.Osbb;

@Mapper(componentModel = "spring")
public interface OsbbMapper {
    @Mapping(target = "login", source = "login")
    @Mapping(target = "password", source = "password")
    Osbb mapToOsbb(OsbbDTO osbbDTO);

    @Mapping(target = "login", source = "login")
    @Mapping(target = "password", source = "password")
    OsbbDTO mapToOsbbDTO(Osbb osbb);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchMerge(OsbbDTO osbbDTO, @MappingTarget Osbb osbb);

}
