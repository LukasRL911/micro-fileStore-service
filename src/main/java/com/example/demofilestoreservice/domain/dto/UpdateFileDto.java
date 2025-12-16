package com.example.demofilestoreservice.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateFileDto(

        @NotBlank(message = "The Name is mandatory")
        String fileNameDto,

        String filePathDto

) {
}
