package com.yeoljeong.tripmate.record.application.dto.command;

import com.yeoljeong.tripmate.record.domain.constants.VisibilityType;
import java.util.UUID;
import lombok.Builder;

@Builder
public record FeedVisibilityCommand(
    UUID userId,
    UUID feedId,
    VisibilityType visibilityType
) {

}
