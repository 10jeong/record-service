package com.yeoljeong.tripmate.record.application.dto.result;

import com.yeoljeong.tripmate.record.domain.model.Feed;
import java.util.List;
import lombok.Builder;

@Builder
public record FeedListResult(
    List<FeedDetailResult> results
) {

  public static FeedListResult from(List<Feed> feeds) {
    return FeedListResult.builder()
        .results(feeds.stream().map(FeedDetailResult::from).toList())
        .build();
  }
}
