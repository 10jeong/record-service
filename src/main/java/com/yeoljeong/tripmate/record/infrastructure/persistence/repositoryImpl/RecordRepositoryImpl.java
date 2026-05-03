package com.yeoljeong.tripmate.record.infrastructure.persistence.repositoryImpl;

import com.yeoljeong.tripmate.record.domain.constants.VisibilityType;
import com.yeoljeong.tripmate.record.domain.model.Feed;
import com.yeoljeong.tripmate.record.domain.repository.RecordRepository;
import com.yeoljeong.tripmate.record.infrastructure.persistence.jpa.FeedJpaRepository;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RecordRepositoryImpl implements RecordRepository {

  private final FeedJpaRepository feedJpaRepository;

  @Override
  public Feed saveForFeed(Feed feed) {
    return feedJpaRepository.save(feed);
  }

  @Override
  public Optional<Feed> findFeedDataById(UUID feedId) {
    return feedJpaRepository.findByIdAndIsDeletedIsFalse(feedId);
  }

  @Override
  public List<Feed> findFeedListByCondition(UUID userId, UUID planUnitId,
      boolean isPlanUnitMember) {
    Specification<Feed> specification = (root, query, cb) -> {
      List<Predicate> predicates = new ArrayList<>();

      predicates.add(cb.equal(root.get("planUnitId"), planUnitId));

      Path<VisibilityType> visibility = root.get("visibilityType");

      Predicate publicFeed = cb.equal(visibility, VisibilityType.PUBLIC);

      Predicate groupFeed = cb.and(
          cb.equal(visibility, VisibilityType.GROUP),
          cb.isTrue(cb.literal(isPlanUnitMember))
      );

      Predicate privateOwnFeed = cb.and(
          cb.equal(visibility, VisibilityType.PRIVATE),
          cb.equal(root.get("userId"), userId)
      );
      predicates.add(cb.isFalse(root.get("isDeleted")));

      predicates.add(cb.or(
          publicFeed,
          groupFeed,
          privateOwnFeed
      ));

      return cb.and(predicates.toArray(new Predicate[0]));
    };
    return feedJpaRepository.findAll(specification);
  }
}
