package com.yeoljeong.tripmate.record.application.client;

import java.util.UUID;

public interface PlanClient {

  boolean validateGroupMember(UUID userId, UUID planUnitId);
}
