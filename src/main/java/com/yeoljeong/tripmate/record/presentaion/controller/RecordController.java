package com.yeoljeong.tripmate.record.presentaion.controller;

import com.yeoljeong.tripmate.auth.annotation.LoginUser;
import com.yeoljeong.tripmate.auth.context.UserContext;
import com.yeoljeong.tripmate.record.application.service.command.RecordCommandService;
import com.yeoljeong.tripmate.record.application.service.query.RecordQueryService;
import com.yeoljeong.tripmate.record.presentaion.dto.request.FeedCreateRequest;
import com.yeoljeong.tripmate.record.presentaion.dto.request.FeedVisibilityRequest;
import com.yeoljeong.tripmate.record.presentaion.dto.response.FeedBaseResponse;
import com.yeoljeong.tripmate.record.presentaion.dto.response.FeedDetailResponse;
import com.yeoljeong.tripmate.record.presentaion.dto.response.FeedListResponse;
import com.yeoljeong.tripmate.response.ApiResponse;
import com.yeoljeong.tripmate.response.constants.CommonSuccessCode;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/feeds")
public class RecordController {

  private final RecordCommandService recordCommandService;
  private final RecordQueryService recordQueryService;

  @PostMapping()
  public ApiResponse<FeedDetailResponse> createFeedData(
      @LoginUser UserContext userContext,
      @Validated @ModelAttribute FeedCreateRequest request
  ) {
    return ApiResponse.success(
        CommonSuccessCode.OK,
        FeedDetailResponse.from(
            recordCommandService.createFeedImage(
                request.toCommand(UUID.fromString(userContext.userId()))
            ))
    );
  }

  @PatchMapping("/{feedId}/visibility")
  public ApiResponse<FeedBaseResponse> updateFeedVisibility(
      @LoginUser UserContext userContext,
      @PathVariable UUID feedId,
      @Validated @RequestBody FeedVisibilityRequest request
  ) {
    return ApiResponse.success(
        CommonSuccessCode.OK,
        FeedBaseResponse.from(
            recordCommandService.updateFeedVisibility(
                request.toCommand(UUID.fromString(userContext.userId()), feedId)
            )
        )
    );
  }

  @GetMapping("/{planUnitId}")
  public ApiResponse<FeedListResponse> getFeedListDataByPlan(
      @LoginUser UserContext userContext,
      @PathVariable UUID planUnitId
  ) {
    return ApiResponse.success(
        CommonSuccessCode.OK,
        FeedListResponse.from(
            recordQueryService.getFeedListDataByPlan(
                UUID.fromString(userContext.userId()),
                planUnitId
            )
        ));
  }

  @GetMapping("/me")
  public ApiResponse<FeedListResponse> getMyFeedList(
      @LoginUser UserContext userContext
  ) {
    return ApiResponse.success(
        CommonSuccessCode.OK,
        FeedListResponse.from(
            recordQueryService.getMyFeedList(
                UUID.fromString(userContext.userId())
            )
        )
    );
  }
}
