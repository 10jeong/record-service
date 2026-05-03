package com.yeoljeong.tripmate.record.application.service.command;

import com.yeoljeong.tripmate.record.application.dto.command.FeedCreateCommand;
import com.yeoljeong.tripmate.record.application.dto.command.FeedVisibilityCommand;
import com.yeoljeong.tripmate.record.application.dto.result.FeedBaseResult;
import com.yeoljeong.tripmate.record.application.dto.result.FeedDetailResult;

public interface RecordCommandService {

  FeedDetailResult createFeedImage(FeedCreateCommand command);

  FeedBaseResult updateFeedVisibility(FeedVisibilityCommand command);
}
