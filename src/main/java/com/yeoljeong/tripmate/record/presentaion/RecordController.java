package com.yeoljeong.tripmate.record.presentaion;

import com.yeoljeong.tripmate.auth.annotation.LoginUser;
import com.yeoljeong.tripmate.auth.context.UserContext;
import com.yeoljeong.tripmate.record.presentaion.dto.request.FeedCreateRequest;
import com.yeoljeong.tripmate.record.presentaion.dto.response.FeedCreateResponse;
import com.yeoljeong.tripmate.record.service.command.RecordCommandService;
import com.yeoljeong.tripmate.response.ApiResponse;
import com.yeoljeong.tripmate.response.constants.CommonSuccessCode;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/feeds")
public class RecordController {

  private final RecordCommandService recordCommandService;

  @PostMapping()
  public ApiResponse<FeedCreateResponse> createFeedData(
      @LoginUser UserContext userContext,
      @ModelAttribute FeedCreateRequest request
  ) {
    return ApiResponse.success(
        CommonSuccessCode.OK,
        FeedCreateResponse.from(
            recordCommandService.createFeedImage(
                request.toCommand(UUID.fromString(userContext.userId()))
            ))
    );
  }
}
