package com.muqi.backendsl.service.impl;

import com.muqi.backendsl.constant.GPTCostant;
import com.muqi.backendsl.service.GPTService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.muqi.backendsl.constant.GPTCostant.*;

@Service
public class GPTServiceImpl implements GPTService {

    @Override
    public String getGPTPrompt(String tag) {
        return prefix + tag + suffix;
    }


    private List<String> parseGPTResponse(String response) {
        /** parse GPT response string into multiple urls, each url in response is wrapped in () **/
        List<String> urls = new ArrayList<>();
        Matcher matcher = Pattern.compile("\\((.*?)\\)").matcher(response);
        while (matcher.find()) {
            String url = matcher.group(1);
            if (url.contains("http")) urls.add(url);
        }
        return urls;
    }


    @Override
    @SneakyThrows
    public List<String> getGPTRecommandation(String prompt) {
        Socket socket = new Socket(serverAddress, serverPort);
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        /** write prompt to the GPT server **/
        outputStream.write(prompt.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();

        /** read response from GPT server **/
        StringBuilder response = new StringBuilder();
        int read;
        byte[] buffer = new byte[bufferSize];
        while((read = inputStream.read(buffer)) != -1) {
            String output = new String(buffer, 0, read);
            response.append(output);
        };
        inputStream.close();
        outputStream.close();
        socket.close();

        return parseGPTResponse(response.toString());
    }
}
