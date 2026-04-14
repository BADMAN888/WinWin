package vk.winwinserviceb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vk.winwinserviceb.dto.TransformRequest;
import vk.winwinserviceb.dto.TransformResponse;
import vk.winwinserviceb.service.TransformService;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TransformController {

    private final TransformService transformService;

    @Value("${internal.token}")
    private String internalToken;

    @PostMapping("/transform")
    public ResponseEntity<TransformResponse> transform(
            @RequestHeader(value = "X-Internal-Token", required = false) String token,
            @RequestBody TransformRequest request
    ) {

        if (token == null || !token.equals(internalToken)) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(transformService.transform(request));
    }
}