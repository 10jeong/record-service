package com.yeoljeong.tripmate.record.infrastructure.external;

import com.yeoljeong.tripmate.record.application.client.PlanClient;
import com.yeoljeong.tripmate.record.infrastructure.external.dto.PlanParticipationResponse;
import feign.FeignException;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlanAdapter implements PlanClient {

  private static final Logger log = LogManager.getLogger(PlanAdapter.class);
  private final PlanFeignClient planFeignClient;

  @Override
  public boolean validateGroupMember(UUID userId, UUID planUnitId) {
    try {
      PlanParticipationResponse planParticipationResponse = planFeignClient.getPlanParticipation(
          planUnitId, userId);
      if (planParticipationResponse == null) {
        return false;
      }
      if (Objects.equals(planParticipationResponse.userId(), userId)
          && Objects.equals(planParticipationResponse.status(), "JOINED")) {
        return true;
      }
    } catch (FeignException e) {
      return false;
    }
    return false;
  }
}

