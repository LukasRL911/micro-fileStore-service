package com.example.demofilestoreservice.persistence.entity;

import com.example.demofilestoreservice.domain.FileCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "file_store")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 130)
    @Column(nullable = false, length = 130, unique = true)
    private String fileName;

    @NotBlank
    @Size(max = 500)
    @Column(nullable = false, length = 500)
    private String filePath;

    @Min(0)
    @Column(nullable = false)
    private Long fileSize;

    @NotBlank
    @Size(max = 200)
    @Column(nullable = false, length = 200)
    private String fileType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FileStatus fileStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FileCategory fileCategory;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
