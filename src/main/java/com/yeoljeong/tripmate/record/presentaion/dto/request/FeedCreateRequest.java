package com.yeoljeong.tripmate.record.presentaion.dto.request;

import com.yeoljeong.tripmate.record.application.dto.command.FeedCreateCommand;
import com.yeoljeong.tripmate.record.domain.constants.VisibilityType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Data // Getter, Setter 포함
@Builder
@NoArgsConstructor // @ModelAttribute를 위해 필수
@AllArgsConstructor

public class FeedCreateRequest {

  @NotNull
  private UUID planUnitId;

  @NotEmpty
  private List<MultipartFile> originImages;

  @NotNull
  @NotBlank
  @Length(max = 20)
  private String title;

  @NotNull
  @NotBlank
  @Length(max = 255)
  private String description;

  @NotNull
  private VisibilityType visibilityType;

  public FeedCreateCommand toCommand(UUID userId) {
    return FeedCreateCommand.builder()
        .userId(userId)
        .planUnitId(this.planUnitId)
        .originImages(this.originImages)
        .title(this.title)
        .description(this.description)
        .visibilityType(this.visibilityType)
        .build();
  }
}