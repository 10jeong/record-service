package com.yeoljeong.tripmate.record.service.command.impl;

import com.yeoljeong.tripmate.record.application.client.StorageClient;
import com.yeoljeong.tripmate.record.application.dto.command.FeedCreateCommand;
import com.yeoljeong.tripmate.record.application.dto.result.FeedCreateResult;
import com.yeoljeong.tripmate.record.domain.model.Feed;
import com.yeoljeong.tripmate.record.domain.model.FeedImage;
import com.yeoljeong.tripmate.record.domain.repository.RecordRepository;
import com.yeoljeong.tripmate.record.service.command.RecordCommandService;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class RecordCommandServiceImpl implements RecordCommandService {

  private final StorageClient storageClient;
  private final RecordRepository recordRepository;

  private String uploadImage(MultipartFile image, UUID planUnitId, UUID userId) {
    String fileName = "feed-" + planUnitId + "-" + userId + ".jpg";
    return storageClient.upload(image, fileName);
  }

  @Transactional
  public FeedCreateResult createFeedImage(FeedCreateCommand command) {
    Feed feed = Feed.create(
        command.userId(),
        command.planUnitId(),
        command.title(),
        command.description(),
        command.visibilityType());

    for (MultipartFile file : command.originImages()) {
      try {
        String uploadUrl = uploadImage(file, command.planUnitId(), command.userId());
        feed.updateFeedImages(new FeedImage(uploadUrl));
      } catch (Exception ignored) {
      }
    }
    return FeedCreateResult.from(recordRepository.saveForFeed(feed));
  }
}
