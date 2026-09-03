package com.example.config;


import com.example.exception.CustomException;
import com.example.exception.ErrorResponse;
import feign.Response;
import org.springframework.context.annotation.Configuration;
import feign.codec.ErrorDecoder;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;


public class CustomErrorDecoder implements ErrorDecoder{


    @Override
    public Exception decode(String s, Response response) {

        ObjectMapper objectMapper=new ObjectMapper();
//        objectMapper.findAndRegisterModules();
        try {
            InputStream is = response.body().asInputStream();
            ErrorResponse errorResponse = objectMapper.readValue(is, ErrorResponse.class);
            return new CustomException(errorResponse.getMessage(), errorResponse.getStatus());
        } catch (IOException e) {
            throw new CustomException("INTER_SERVER_ERROR");
        }
    }
}
