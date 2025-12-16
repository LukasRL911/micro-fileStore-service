package com.example.demofilestoreservice.domain.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface FileStoreAiService {

    @UserMessage("""
                Generate a fun fact about inflation over time, as interesting as you want it to be,
                use no more than 200 characters.
            """)
    String generateFunFact(@V("platform") String platform);

    @SystemMessage("""
                    Analyze the user's files in this app and suggest one practical, simple tip\s
                    for organizing, naming, or using their files. Keep it friendly and short,\s
                    no more than 150 characters.
            """)
    String generateSuggestions(@UserMessage String userMessage);

}
