package com.yeoljeong.tripmate.record.domain.repository;

import com.yeoljeong.tripmate.record.domain.model.Feed;

public interface RecordRepository {

  Feed saveForFeed(Feed feed);
}
