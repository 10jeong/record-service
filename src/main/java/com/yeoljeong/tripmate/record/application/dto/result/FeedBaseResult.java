package com.yeoljeong.tripmate.record.application.dto.result;

import com.yeoljeong.tripmate.record.domain.model.Feed;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;

@Builder
public record FeedBaseResult(
    UUID feedId,
    LocalDateTime updateAt
) {

  public static FeedBaseResult from(Feed feed) {
    return FeedBaseResult.builder()
        .feedId(feed.getId())
        .updateAt(feed.getUpdatedAt())
        .build();
  }
}
