package com.example.demofilestoreservice.domain.dto;

import com.example.demofilestoreservice.domain.FileCategory;
import com.example.demofilestoreservice.persistence.entity.FileStatus;

public record FileDto(

        Long id,
        String fileNameDto,
        String filePathDto,
        Long fileSizeDto,
        String fileTypeDto,
        FileCategory fileCategoryDto

) {
}
