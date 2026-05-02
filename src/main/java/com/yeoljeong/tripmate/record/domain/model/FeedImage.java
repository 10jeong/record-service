package com.yeoljeong.tripmate.record.domain.model;

import com.yeoljeong.tripmate.domain.BaseAuditEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "p_feed_image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedImage extends BaseAuditEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String imageKey;

  public FeedImage(String imageKey) {
    this.imageKey = imageKey;
  }

  public static FeedImage create(String imageKey) {
    return new FeedImage(imageKey);
  }
}
