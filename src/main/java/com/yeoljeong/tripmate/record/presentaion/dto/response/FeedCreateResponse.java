package com.yeoljeong.tripmate.record.presentaion.dto.response;

import com.yeoljeong.tripmate.record.application.dto.result.FeedCreateResult;
import com.yeoljeong.tripmate.record.domain.constants.VisibilityType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;

@Builder
public record FeedCreateResponse(
    UUID planUnitId,
    UUID feedId,
    List<String> imageUrls,
    VisibilityType visibilityType,
    String title,
    String description,
    LocalDateTime createdAt
) {

  public static FeedCreateResponse from(FeedCreateResult result) {
    return FeedCreateResponse.builder()
        .planUnitId(result.planUnitId())
        .feedId(result.feedId())
        .imageUrls(result.imageUrls())
        .visibilityType(result.visibilityType())
        .title(result.title())
        .description(result.description())
        .createdAt(result.createdAt()).build();
  }
}
