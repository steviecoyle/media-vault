package com.scoyle.media_vault.service.impl;

import com.scoyle.media_vault.persistence.entity.GameEntity;
import com.scoyle.media_vault.persistence.repository.GamesRepository;
import com.scoyle.media_vault.service.GamesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Service
class GamesServiceImpl implements GamesService {

    private final GamesRepository repository;

    @Override
    public Page<GameEntity> getAllGames(Pageable pageable) {

        return repository.findAll(pageable);
    }

    @Override
    public GameEntity getGameByUuid(String uuid) {
        Optional<GameEntity> optionalGame = repository.findGameByUuid(uuid);

        if (optionalGame.isPresent()) {
            return optionalGame.get();
        } else {
            log.error("No Game by that UUID");
            return new GameEntity();
        }
    }
}
