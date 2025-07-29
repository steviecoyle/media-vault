package com.scoyle.media_vault.service;

import com.scoyle.media_vault.persistence.entity.GameEntity;
import com.scoyle.media_vault.request.AddGameRequest;
import com.scoyle.media_vault.request.UpdateGameRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GamesService {

    Page<GameEntity> getAllGames(Pageable pageable);

    GameEntity getGameByUuid(String uuid);

    GameEntity createGame(AddGameRequest addGameRequest);

    GameEntity updateGame(UpdateGameRequest updateGameRequest);

    void deleteGame(final String uuid);
}
