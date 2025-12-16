package com.example.demofilestoreservice.persistence.mapper;

import ch.qos.logback.core.model.ComponentModel;
import com.example.demofilestoreservice.domain.dto.FileDto;
import com.example.demofilestoreservice.domain.dto.UpdateFileDto;
import com.example.demofilestoreservice.persistence.entity.FileEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {FileCategoryMapper.class})
public interface FileMapper {

    @Mapping(source = "fileName", target = "fileNameDto")
    @Mapping(source = "filePath", target = "filePathDto")
    @Mapping(source = "fileSize", target = "fileSizeDto")
    @Mapping(source = "fileType", target = "fileTypeDto")
    @Mapping(source = "fileCategory", target = "fileCategoryDto", qualifiedByName = "stringToFileCategory")
    FileDto toDto(FileEntity fileEntity);
    List<FileDto> toDto(Iterable<FileEntity> entities);

    @InheritInverseConfiguration
    @Mapping(source = "fileCategoryDto", target = "fileCategory", qualifiedByName = "fileCategoryToString")
    FileEntity toEntity(FileDto dto);

    @Mapping(target = "fileName", source = "fileNameDto")
    @Mapping(target = "filePath", source = "filePathDto")
    void updateEntityFromDto(UpdateFileDto updateFileDto, @MappingTarget FileEntity entity);



}
