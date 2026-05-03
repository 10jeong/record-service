package com.yeoljeong.tripmate.record.presentaion.dto.response;

import com.yeoljeong.tripmate.record.application.dto.result.FeedListResult;
import java.util.List;
import lombok.Builder;

@Builder
public record FeedListResponse(
    List<FeedDetailResponse> responses

) {

  public static FeedListResponse from(FeedListResult result) {
    return FeedListResponse.builder()
        .responses(result.results().stream().map(FeedDetailResponse::from).toList()).build();
  }
}
