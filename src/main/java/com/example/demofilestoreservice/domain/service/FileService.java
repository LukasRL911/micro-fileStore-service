package com.example.demofilestoreservice.domain.service;

import com.example.demofilestoreservice.domain.dto.FileDto;
import com.example.demofilestoreservice.domain.dto.UpdateFileDto;
import com.example.demofilestoreservice.domain.repository.FileRepository;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Tool("Search for All files in the platform")
    public List<FileDto> getAll(){

        return this.fileRepository.getAll();

    }

    public FileDto getById(long id){
        return this.fileRepository.getById(id);
    }
    public FileDto add(FileDto fileDto){
        return this.fileRepository.save(fileDto);
    }

    public FileDto update(long id, UpdateFileDto updateFileDto){
        return this.fileRepository.update(id, updateFileDto);
    }

    public void delete(long id) {
        this.fileRepository.delete(id);
    }

}
