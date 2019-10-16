package com.example.net.service;

import com.example.net.dto.BaseRequestDTO;
import com.example.net.dto.BaseResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.SocketTimeoutException;

@Service
@Slf4j
@RequiredArgsConstructor
public class HttpClientService {

    @Value("${testServer.Address}")
    private String serviceAddress;

    private final String SIMPLEPOST="/simplePost";

    private final ObjectMapper mapper;

    public BaseResponseDTO simplePost(BaseRequestDTO request) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(300 * 1000)
                    .setConnectTimeout(300 * 1000)
                    .build();
            String url = serviceAddress + SIMPLEPOST;
            HttpPost post = new HttpPost(url);
            post.setConfig(requestConfig);
            post.setHeader("Content-Type", "application/json;charset=utf-8");
            StringEntity postingString = new StringEntity(mapper.writeValueAsString(request),
                    "utf-8");
            post.setEntity(postingString);

            log.info(">> simplePost input:{}", postingString);
            HttpResponse response = httpClient.execute(post);
            String content = EntityUtils.toString(response.getEntity());
            log.info(">> simplePost ouput:{}", content);

            BaseResponseDTO responseDTO = mapper.readValue(content, BaseResponseDTO.class);

            return responseDTO;

        } catch (SocketTimeoutException e) {
            log.error(e.toString(), e);
        } catch (Exception e) {
            log.error(e.toString(), e);
        }

        return null;
    }
}
