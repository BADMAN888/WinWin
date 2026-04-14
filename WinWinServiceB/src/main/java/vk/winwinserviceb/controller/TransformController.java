package vk.winwinserviceb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vk.winwinserviceb.dto.TransformRequest;
import vk.winwinserviceb.dto.TransformResponse;
import vk.winwinserviceb.service.TransformService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TransformController {

    private static final String HEADER = "X-Internal-Token";

    private final TransformService transformService;

    @Value("${internal.token}")
    private String internalToken;


    @PostMapping("/transform")
    public ResponseEntity<TransformResponse> transform(
            @RequestHeader(value = HEADER, required = false) String token,
            @RequestBody TransformRequest request
    ) {
        if (!internalToken.equals(token)) {
            return ResponseEntity.status(403).build();
        }

        String result = transformService.transform(request.text());

        return ResponseEntity.ok(new TransformResponse(result));
    }
}