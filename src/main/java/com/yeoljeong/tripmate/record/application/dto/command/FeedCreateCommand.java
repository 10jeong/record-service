package com.yeoljeong.tripmate.record.application.dto.command;

import com.yeoljeong.tripmate.record.domain.constants.VisibilityType;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record FeedCreateCommand(
    UUID userId,
    UUID planUnitId,
    List<MultipartFile> originImages,
    String title,
    String description,
    VisibilityType visibilityType
) {

}
