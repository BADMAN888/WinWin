package vk.winwinserviceb.service;

import org.springframework.stereotype.Service;

@Service
public class TransformService {

    public String transform(String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Text cannot be empty");
        }

        return new StringBuilder(text)
                .reverse()
                .toString()
                .toUpperCase();
    }
}