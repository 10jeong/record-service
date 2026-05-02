package com.yeoljeong.tripmate.record.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageBucket {

  @Column(nullable = false, name = "image_url")
  private String imageKey;

  public ImageBucket(String imageKey) {
    this.imageKey = imageKey;
  }
}