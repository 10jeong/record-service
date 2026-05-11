package com.yeoljeong.tripmate.record.infrastructure.external.dto;

import java.util.UUID;

public record PlanParticipationResponse(
    UUID planUnitId,
    UUID userId,
    String status
) {

}
