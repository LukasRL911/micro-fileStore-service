package com.example.demofilestoreservice.web.controller;

import com.example.demofilestoreservice.domain.dto.FileDto;
import com.example.demofilestoreservice.domain.dto.SuggestRequestDto;
import com.example.demofilestoreservice.domain.dto.UpdateFileDto;
import com.example.demofilestoreservice.domain.service.FileService;
import com.example.demofilestoreservice.domain.service.FileStoreAiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;
    private final FileStoreAiService fileStoreAiService;

    public FileController(FileService fileService, FileStoreAiService fileStoreAiService) {
        this.fileService = fileService;
        this.fileStoreAiService = fileStoreAiService;
    }

    @PostMapping("/suggest")
    public ResponseEntity<String> generateSuggestion(@RequestBody SuggestRequestDto suggestRequestDto) {

        return ResponseEntity.ok(this.fileStoreAiService.generateSuggestions(suggestRequestDto.userPreferences()));

    }

    @GetMapping
    public ResponseEntity<List<FileDto>> getFiles() {

        return ResponseEntity.ok(this.fileService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(

            summary = "Obtain a File Through its id",
            description = "Return the film that matches the given identifier",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Found file"),
                    @ApiResponse(responseCode = "404", description = "Not found film", content = @Content)
            }

    )
    public ResponseEntity<FileDto> getFileById(@Parameter(description = "Film´s identifier to retrieve", example = "9") @PathVariable long id) {

        FileDto fileDto = this.fileService.getById(id);

        if (fileDto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(fileDto);

    }


    @PostMapping()
    public ResponseEntity<FileDto> addFile(@RequestBody FileDto fileDto) {

        FileDto fileDtoResponse = this.fileService.add(fileDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.fileService.add(fileDtoResponse));

    }

    @PutMapping("/{id}")
    public  ResponseEntity<FileDto> updateFile(@PathVariable long id, @RequestBody @Valid UpdateFileDto updateFileDto) {
        return ResponseEntity.ok(this.fileService.update(id, updateFileDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable long id) {
        this.fileService.delete(id);
        return ResponseEntity.ok().build();
    }

}
