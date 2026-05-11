package com.yeoljeong.tripmate.record.presentaion.dto.response;

import com.yeoljeong.tripmate.record.application.dto.result.FeedBaseResult;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;

@Builder
public record FeedBaseResponse(
    UUID feedId,
    LocalDateTime updateAt
) {

  public static FeedBaseResponse from(FeedBaseResult result) {
    return FeedBaseResponse.builder()
        .feedId(result.feedId())
        .updateAt(result.updateAt()).build();
  }
}
