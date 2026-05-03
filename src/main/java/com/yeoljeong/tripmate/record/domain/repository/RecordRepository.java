package com.yeoljeong.tripmate.record.domain.repository;

import com.yeoljeong.tripmate.record.domain.model.Feed;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecordRepository {

  Feed saveForFeed(Feed feed);

  Optional<Feed> findFeedDataById(UUID feedId);

  List<Feed> findFeedListByCondition(UUID userId, UUID planUnitId, boolean isPlanUnitMember);
}
