package com.yeoljeong.tripmate.record.application.service.query;

import com.yeoljeong.tripmate.record.application.dto.result.FeedListResult;
import java.util.UUID;

public interface RecordQueryService {

  FeedListResult getFeedListDataByPlan(UUID uuid, UUID planUnitId);
}
