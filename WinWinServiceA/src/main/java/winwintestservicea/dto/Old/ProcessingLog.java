package winwintestservicea.dto.Old;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProcessingLog(
        UUID userId,
        String inputText,
        String outputText,
        LocalDateTime createdAt
) {}
