package com.scoyle.media_vault.persistence.repository;

import com.scoyle.media_vault.persistence.entity.PublishersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<PublishersEntity, Long> {
}
