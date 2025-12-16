package com.example.demofilestoreservice.web.controller;


import com.example.demofilestoreservice.domain.service.FileStoreAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final String platform;
    private final FileStoreAiService AiService  ;

    public HelloController(@Value("${spring.application.name}") String platform, FileStoreAiService newAiService) {

        this.platform = platform;
        this.AiService = newAiService;

    }

    @GetMapping("/hello")
    public String Hello() {

        return this.AiService.generateFunFact(platform);

    }

}
