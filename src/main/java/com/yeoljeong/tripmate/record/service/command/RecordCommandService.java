package com.yeoljeong.tripmate.record.service.command;

import com.yeoljeong.tripmate.record.application.dto.command.FeedCreateCommand;
import com.yeoljeong.tripmate.record.application.dto.command.FeedVisibilityCommand;
import com.yeoljeong.tripmate.record.application.dto.result.FeedBaseResult;
import com.yeoljeong.tripmate.record.application.dto.result.FeedCreateResult;

public interface RecordCommandService {

  FeedCreateResult createFeedImage(FeedCreateCommand command);

  FeedBaseResult updateFeedVisibility(FeedVisibilityCommand command);
}
