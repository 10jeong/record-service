package com.yeoljeong.tripmate.record.application.dto.result;

import com.yeoljeong.tripmate.record.domain.constants.VisibilityType;
import com.yeoljeong.tripmate.record.domain.model.Feed;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record FeedDetailResult(
    UUID planUnitId,
    UUID feedId,
    List<String> imageUrls,
    VisibilityType visibilityType,
    String title,
    String description,
    LocalDateTime createdAt
) {

  public static FeedDetailResult from(Feed feed) {
    return FeedDetailResult.builder()
        .planUnitId(feed.getPlanUnitId())
        .feedId(feed.getId())
        .imageUrls(feed.getFeedImages())
        .visibilityType(feed.getVisibilityType())
        .title(feed.getTitle())
        .description(feed.getDescription())
        .createdAt(feed.getCreatedAt()).build();
  }
}
