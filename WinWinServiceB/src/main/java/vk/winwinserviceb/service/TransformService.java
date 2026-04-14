package vk.winwinserviceb.service;

import org.springframework.stereotype.Service;
import vk.winwinserviceb.dto.TransformRequest;
import vk.winwinserviceb.dto.TransformResponse;

@Service
public class TransformService {

    public TransformResponse transform(TransformRequest request) {

        String text = request.text() == null ? "" : request.text();

        String result = new StringBuilder(text)
                .reverse()
                .toString();

        return new TransformResponse(result);
    }
}