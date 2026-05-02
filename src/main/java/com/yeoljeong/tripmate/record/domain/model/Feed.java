package com.yeoljeong.tripmate.record.domain.model;

import com.yeoljeong.tripmate.domain.BaseAuditEntity;
import com.yeoljeong.tripmate.record.domain.constants.VisibilityType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "p_feed")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Feed extends BaseAuditEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private UUID userId;

  @Column(nullable = false)
  private UUID planUnitId;

  private String title;

  private String description;

  @Enumerated(EnumType.STRING)
  private VisibilityType visibilityType;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "feed_id")
  private List<FeedImage> feedImages;

  public Feed(UUID userId, UUID planUnitId, String title, String description,
      VisibilityType visibilityType) {
    this.userId = userId;
    this.planUnitId = planUnitId;
    this.title = title;
    this.description = description;
    this.visibilityType = visibilityType;
    this.feedImages = new ArrayList<>();
  }

  public static Feed create(UUID userId, UUID planUnitId, String title, String description,
      VisibilityType visibilityType) {
    return new Feed(userId, planUnitId, title, description, visibilityType);
  }

  public void updateFeedImages(FeedImage image) {
    this.feedImages.add(image);
  }

  public List<String> getFeedImages() {
    return feedImages.stream().map(FeedImage::getImageKey).toList();
  }
}
