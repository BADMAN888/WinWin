package winwintestservicea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import winwintestservicea.client.DataApiClient;
import winwintestservicea.dto.ProcessRequest;
import winwintestservicea.dto.ProcessResponse;
import winwintestservicea.entity.ProcessingLogEntity;
import winwintestservicea.repository.ProcessingLogRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProcessService {

    private final DataApiClient dataApiClient;
    private final ProcessingLogRepository logRepository;

    public ProcessResponse process(ProcessRequest request, UUID userId) {

        String result = dataApiClient.transform(request.text());

        ProcessingLogEntity log = ProcessingLogEntity.builder()
                .userId(userId)
                .inputText(request.text())
                .outputText(result)
                .createdAt(LocalDateTime.now())
                .build();

        logRepository.save(log);

        return new ProcessResponse(result);
    }
}