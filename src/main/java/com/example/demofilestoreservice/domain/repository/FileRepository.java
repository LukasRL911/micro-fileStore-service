package com.example.demofilestoreservice.domain.repository;

import com.example.demofilestoreservice.domain.dto.FileDto;
import com.example.demofilestoreservice.domain.dto.UpdateFileDto;

import java.util.List;

public interface FileRepository {

    List<FileDto> getAll();

    FileDto getById(long id);

    FileDto save(FileDto fileDto);

    FileDto update(long id, UpdateFileDto updateFileDto);

    void delete(long id);

}
