package com.yeoljeong.tripmate.record.infrastructure.persistence.repositoryImpl;

import com.yeoljeong.tripmate.record.domain.model.Feed;
import com.yeoljeong.tripmate.record.domain.repository.RecordRepository;
import com.yeoljeong.tripmate.record.infrastructure.persistence.jpa.FeedJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RecordRepositoryImpl implements RecordRepository {

  private final FeedJpaRepository feedJpaRepository;

  @Override
  public Feed saveForFeed(Feed feed) {
    return feedJpaRepository.save(feed);
  }
}
