package com.yeoljeong.tripmate.record.presentaion.dto.request;

import com.yeoljeong.tripmate.record.application.dto.command.FeedVisibilityCommand;
import com.yeoljeong.tripmate.record.domain.constants.VisibilityType;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record FeedVisibilityRequest(
    @NotNull
    VisibilityType visibilityType
) {

  public FeedVisibilityCommand toCommand(UUID userId, UUID feedId) {
    return FeedVisibilityCommand.builder()
        .userId(userId)
        .feedId(feedId)
        .visibilityType(visibilityType).build();
  }
}
