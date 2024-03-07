package ua.QRescue.mapper;

import org.mapstruct.*;
import ua.QRescue.dto.OsbbDTO;
import ua.QRescue.models.Osbb;

@Mapper(componentModel = "spring")
public interface OsbbMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    @Mapping(target = "password", source = "password")
    Osbb mapToOsbb(OsbbDTO osbbDTO);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    @Mapping(target = "password", source = "password")
    OsbbDTO mapToOsbbDTO(Osbb osbb);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void patchMerge(OsbbDTO osbbDTO, @MappingTarget Osbb osbb);

}
