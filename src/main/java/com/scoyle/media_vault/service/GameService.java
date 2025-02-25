package com.scoyle.media_vault.service;

import com.scoyle.media_vault.persistence.entity.GameEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GameService {

    Page<GameEntity> getAllGames(Pageable pageable);
}
