package com.yeoljeong.tripmate.record.infrastructure.external;

import com.yeoljeong.tripmate.record.infrastructure.external.dto.PlanParticipationResponse;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "plan-service", path = "internal/plan-units")
public interface PlanFeignClient {

  @GetMapping("/{planUnitId}/participations/users/{userId}")
  PlanParticipationResponse getPlanParticipation(
      @PathVariable UUID planUnitId,
      @PathVariable UUID userId);
}
