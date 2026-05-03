package com.yeoljeong.tripmate.record.infrastructure.persistence.jpa;

import com.yeoljeong.tripmate.record.domain.model.Feed;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FeedJpaRepository extends JpaRepository<Feed, UUID>,
    JpaSpecificationExecutor<Feed> {

  Optional<Feed> findByIdAndIsDeletedIsFalse(UUID feedId);
}