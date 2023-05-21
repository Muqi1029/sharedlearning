package com.muqi.backendsl.service;



import java.io.IOException;
import java.util.List;

public interface GPTService {
    String getGPTPrompt(String tag);
    List<String> getGPTRecommandation(String prompt) throws IOException;
}
