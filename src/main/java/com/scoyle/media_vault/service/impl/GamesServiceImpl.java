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

    /**
     * @param pageable containing size, page number and sort information
     * @return Paged list
     */
    @Override
    public Page<GameEntity> getAllGames(Pageable pageable) {

        return repository.findAll(pageable);
    }

    /**
     * @param uuid unique Id used to identify an individual game
     * @return fetched GameEntity object
     */
    @Override
    public GameEntity getGameByUuid(final String uuid) {

        return getGameByUuidInternal(uuid);
    }

    /**
     * @param createGameRequest containing information of a game to add
     * @return saved GameEntity object
     */
    @Override
    public GameEntity createGame(CreateGameRequest createGameRequest) {
        String uuid = "";

        boolean uuidAlreadyExists = true;

        // Repeat until we get a unique UUID
        while (uuidAlreadyExists) {
            uuid = UUID.randomUUID().toString().replace("-", "");
            uuidAlreadyExists = repository.existsByUuid(uuid);
        }

        GameEntity newGame = new GameEntity();
        newGame.setTitle(createGameRequest.getTitle());
        newGame.setDescription(createGameRequest.getDescription());
        newGame.setGenre(createGameRequest.getGenre());
        newGame.setRating(createGameRequest.getRating());
        newGame.setPlatform(createGameRequest.getPlatform());
        newGame.setReleaseDate(createGameRequest.getReleaseDate());
        newGame.setCoverArtLink(createGameRequest.getCoverArt());
        newGame.setUuid(uuid);

        return repository.save(newGame);
    }

    /**
     * @param updateGameRequest
     * @return updated GameEntity object
     */
    @Override
    public GameEntity updateGame(UpdateGameRequest updateGameRequest) {
        GameEntity game = getGameByUuidInternal(updateGameRequest.getUuid());

        // TODO handle call to fetch publisher and developer

        game.setDescription(updateGameRequest.getDescription());
        game.setCoverArtLink(updateGameRequest.getCoverArt());
        game.setTitle(updateGameRequest.getTitle());
        game.setGenre(updateGameRequest.getGenre());
        game.setRating(updateGameRequest.getRating());
        game.setReleaseDate(updateGameRequest.getReleaseDate());

        return repository.save(game);
    }

    /**
     * @param uuid unique Id used to identify an individual game
     */
    @Override
    public void deleteGame(final String uuid) {
        GameEntity game = getGameByUuidInternal(uuid);

        log.info("Deleting Game with UUID of [{}]", uuid);
        repository.delete(game);
    }

    private GameEntity getGameByUuidInternal(final String uuid) {
        Optional<GameEntity> optionalGame = repository.findGameByUuid(uuid);

        if (optionalGame.isPresent()) {
            return optionalGame.get();
        } else {
            log.error(NO_GAME_BY_UUID_ERROR_MESSAGE);
            throw new ResourceNotFoundException("Game with UUID of [" + uuid + "] not found.");
        }
    }
}
