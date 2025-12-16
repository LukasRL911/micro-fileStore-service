package com.example.demofilestoreservice.persistence.crud;

import com.example.demofilestoreservice.persistence.entity.FileEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudFileEntity extends CrudRepository<FileEntity, Long> {

    FileEntity findFirstByFileName(String name);


}
