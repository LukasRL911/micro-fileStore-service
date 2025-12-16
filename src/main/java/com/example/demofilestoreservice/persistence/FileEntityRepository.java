package com.example.demofilestoreservice.persistence;

import com.example.demofilestoreservice.domain.dto.FileDto;
import com.example.demofilestoreservice.domain.dto.UpdateFileDto;
import com.example.demofilestoreservice.domain.exception.FileAlreadyExistsException;
import com.example.demofilestoreservice.domain.repository.FileRepository;
import com.example.demofilestoreservice.persistence.crud.CrudFileEntity;
import com.example.demofilestoreservice.persistence.entity.FileEntity;
import com.example.demofilestoreservice.persistence.entity.FileStatus;
import com.example.demofilestoreservice.persistence.mapper.FileMapper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;

@Repository
public class FileEntityRepository implements FileRepository {

    private final CrudFileEntity crudFileEntity;
    private final FileMapper fileMapper;

    public FileEntityRepository(CrudFileEntity crudFileEntity, FileMapper fileMapper) {
        this.crudFileEntity = crudFileEntity;
        this.fileMapper = fileMapper;
    }

    @Override
    public List<FileDto> getAll() {

        return this.fileMapper.toDto(this.crudFileEntity.findAll());
    }

    @Override
    public FileDto getById(long id) {

        FileEntity fileEntity = this.crudFileEntity.findById(id).orElse(null);
        return this.fileMapper.toDto(fileEntity);
    }

    @Override
    public FileDto save(FileDto fileDto) {

        if (this.crudFileEntity.findFirstByFileName(fileDto.fileNameDto()) != null) {

            throw new FileAlreadyExistsException(fileDto.fileNameDto());

        }

        FileEntity fileEntity = this.fileMapper.toEntity(fileDto);
        fileEntity.setFileStatus(FileStatus.ACTIVE);
        return this.fileMapper.toDto(this.crudFileEntity.save(fileEntity));

    }

    @Override
    public FileDto update(long id, UpdateFileDto updateFileDto) {

        FileEntity fileEntity = this.crudFileEntity.findById(id).orElse(null);

        if (fileEntity == null) return null;

        this.fileMapper.updateEntityFromDto(updateFileDto, fileEntity);

        return this.fileMapper.toDto(this.crudFileEntity.save(fileEntity));
    }

    @Override
    public void delete(long id) {

        this.crudFileEntity.deleteById(id);

    }
}
