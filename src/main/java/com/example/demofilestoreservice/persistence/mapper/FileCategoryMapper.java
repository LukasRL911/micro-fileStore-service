package com.example.demofilestoreservice.persistence.mapper;

import com.example.demofilestoreservice.domain.FileCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public class FileCategoryMapper {


    @Named("stringToFileCategory")
    static FileCategory stringToFileCategory(String fileCategory){
        if(fileCategory==null) return null;
        return switch (fileCategory.toUpperCase()) {
            case "IMAGE" -> FileCategory.IMAGE;
            case "AUDIO" -> FileCategory.AUDIO;
            case "VIDEO" -> FileCategory.VIDEO;
            case "DOCUMENT" -> FileCategory.DOCUMENT;
            case "OTHER" -> FileCategory.OTHER;
            default -> null;
        };
    }

    @Named("fileCategoryToString")
    static String fileCategoryToString(FileCategory fileCategory){
        if(fileCategory==null) return null;
        return switch (fileCategory)  {
            case IMAGE -> "IMAGE";
            case AUDIO -> "AUDIO";
            case VIDEO -> "VIDEO";
            case DOCUMENT -> "DOCUMENT";
            case OTHER -> "OTHER";
            default -> null;
        };
    }

}
