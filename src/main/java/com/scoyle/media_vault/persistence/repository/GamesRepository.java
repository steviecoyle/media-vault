package com.scoyle.media_vault.persistence.repository;

import com.scoyle.media_vault.persistence.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GamesRepository extends JpaRepository<GameEntity, Long> {

    Optional<GameEntity> findGameByUuid(String uuid);
}
