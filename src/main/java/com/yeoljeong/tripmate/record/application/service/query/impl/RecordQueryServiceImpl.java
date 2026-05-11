package com.yeoljeong.tripmate.record.application.service.query.impl;

import com.yeoljeong.tripmate.record.application.dto.result.FeedListResult;
import com.yeoljeong.tripmate.record.application.service.query.RecordQueryService;
import com.yeoljeong.tripmate.record.domain.repository.RecordRepository;
import com.yeoljeong.tripmate.record.infrastructure.external.PlanAdapter;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecordQueryServiceImpl implements RecordQueryService {

  private final PlanAdapter planAdapter;
  private final RecordRepository recordRepository;

  @Override
  @Transactional(readOnly = true)
  public FeedListResult getFeedListDataByPlan(UUID userId, UUID planUnitId) {
    boolean isPlanUnitMember = planAdapter.validateGroupMember(userId, planUnitId);
    return FeedListResult.from(
        recordRepository.findFeedListByCondition(userId, planUnitId, isPlanUnitMember));
  }

  @Override
  @Transactional(readOnly = true)
  public FeedListResult getMyFeedList(UUID userId) {
    return FeedListResult.from(
        recordRepository.findFeedListByUserId(userId)
    );
  }
}
