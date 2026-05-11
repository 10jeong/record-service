package com.yeoljeong.tripmate.record.application.service.command.impl;

import com.yeoljeong.tripmate.exception.BusinessException;
import com.yeoljeong.tripmate.record.application.client.StorageClient;
import com.yeoljeong.tripmate.record.application.dto.command.FeedCreateCommand;
import com.yeoljeong.tripmate.record.application.dto.command.FeedVisibilityCommand;
import com.yeoljeong.tripmate.record.application.dto.result.FeedBaseResult;
import com.yeoljeong.tripmate.record.application.dto.result.FeedDetailResult;
import com.yeoljeong.tripmate.record.application.service.command.RecordCommandService;
import com.yeoljeong.tripmate.record.domain.exception.RecordErrorCode;
import com.yeoljeong.tripmate.record.domain.model.Feed;
import com.yeoljeong.tripmate.record.domain.model.FeedImage;
import com.yeoljeong.tripmate.record.domain.repository.RecordRepository;
import jakarta.transaction.Transactional;
import java.util.Base64;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecordCommandServiceImpl implements RecordCommandService {

  private final StorageClient storageClient;
  private final RecordRepository recordRepository;

  private String uploadImage(MultipartFile image, UUID planUnitId) {
    String fileName = planUnitId + Base64.getDecoder().toString().substring(10) + ".jpg";
    return storageClient.upload(image, fileName);
  }

  @Transactional
  public FeedDetailResult createFeedImage(FeedCreateCommand command) {
    Feed feed = Feed.create(
        command.userId(),
        command.planUnitId(),
        command.title(),
        command.description(),
        command.visibilityType());

    for (MultipartFile file : command.originImages()) {
      try {
        String uploadUrl = uploadImage(file, command.planUnitId());
        feed.updateFeedImages(new FeedImage(uploadUrl));
      } catch (Exception ignored) {
      }
    }
    return FeedDetailResult.from(recordRepository.saveForFeed(feed));
  }

  @Override
  public FeedBaseResult updateFeedVisibility(FeedVisibilityCommand command) {
    Feed feed = recordRepository.findFeedDataById(command.feedId()).orElseThrow(
        () -> new BusinessException(RecordErrorCode.FEED_NOT_FOUND));
    if (!feed.getUserId().equals(command.userId())) {
      throw new BusinessException(RecordErrorCode.FEED_NOT_ACCESSIBLE);
    }

    feed.updateVisibility(command.visibilityType());
    return FeedBaseResult.from(recordRepository.saveForFeed(feed));
  }
}
