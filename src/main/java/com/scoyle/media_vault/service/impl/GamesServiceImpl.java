package com.scoyle.media_vault.service.impl;

import com.scoyle.media_vault.exception.ResourceNotFoundException;
import com.scoyle.media_vault.persistence.entity.GameEntity;
import com.scoyle.media_vault.persistence.repository.GamesRepository;
import com.scoyle.media_vault.request.CreateGameRequest;
import com.scoyle.media_vault.request.UpdateGameRequest;
import com.scoyle.media_vault.service.GamesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@Service
class GamesServiceImpl implements GamesService {

    private static final String NO_GAME_BY_UUID_ERROR_MESSAGE = "No Game by that UUID";

    private final GamesRepository repository;

    @Override
    public Page<GameEntity> getAllGames(Pageable pageable) {

        return repository.findAll(pageable);
    }

    @Override
    public GameEntity getGameByUuid(final String uuid) {
        Optional<GameEntity> optionalGame = repository.findGameByUuid(uuid);

        if (optionalGame.isPresent()) {
            return optionalGame.get();
        } else {
            log.error(NO_GAME_BY_UUID_ERROR_MESSAGE);
            throw new ResourceNotFoundException("Game with UUID of [" + uuid + "] not found.");
        }
    }

    @Override
    public GameEntity createGame(CreateGameRequest createGameRequest) {
        GameEntity newGame = new GameEntity();
        newGame.setTitle(createGameRequest.getTitle());
        newGame.setDescription(createGameRequest.getDescription());
        newGame.setGenre(createGameRequest.getGenre());
        newGame.setRating(createGameRequest.getRating());
        newGame.setPlatform(createGameRequest.getPlatform());
        newGame.setReleaseDate(createGameRequest.getReleaseDate());
        newGame.setCoverArtLink(createGameRequest.getCoverArt());
        newGame.setUuid(UUID.randomUUID().toString().replace("-", ""));

        return repository.save(newGame);
    }

    @Override
    public GameEntity updateGame(UpdateGameRequest updateGameRequest) {
        Optional<GameEntity> optionalGame = repository.findGameByUuid(updateGameRequest.getUuid());

        if (optionalGame.isPresent()) {
            // TODO handle call to fetch publisher and developer
            GameEntity gameEntity = new GameEntity();
            gameEntity.setDescription(updateGameRequest.getDescription());
            gameEntity.setCoverArtLink(updateGameRequest.getCoverArt());
            gameEntity.setTitle(updateGameRequest.getTitle());
            gameEntity.setGenre(updateGameRequest.getGenre());
            gameEntity.setRating(updateGameRequest.getRating());
            gameEntity.setReleaseDate(updateGameRequest.getReleaseDate());

            return repository.save(gameEntity);
        } else {
            log.error(NO_GAME_BY_UUID_ERROR_MESSAGE);
            throw new ResourceNotFoundException("Game with id of [" + "] not found.");
        }
    }

    @Override
    public void deleteGame(final String uuid) {
        Optional<GameEntity> optionalGame = repository.findGameByUuid(uuid);

        if (optionalGame.isPresent()) {
            log.info("Deleting Game with UUID of [{}]", uuid);
            repository.delete(optionalGame.get());
        } else {
            log.error(NO_GAME_BY_UUID_ERROR_MESSAGE);
            throw new ResourceNotFoundException("Game with id of [" + "] not found.");
        }
    }
}
