package com.scoyle.media_vault.controller;

import com.scoyle.media_vault.persistence.entity.GameEntity;
import com.scoyle.media_vault.request.AddGameRequest;
import com.scoyle.media_vault.request.UpdateGameRequest;
import com.scoyle.media_vault.response.PagedResponse;
import com.scoyle.media_vault.service.GamesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/games")
public class GamesController {

    private final GamesService gameService;

    @GetMapping
    public ResponseEntity<PagedResponse> getAllGames(
            @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "20", required = false) Integer size,
            @RequestParam(name = "sortBy", defaultValue = "title",required = false) String sortBy) {

        log.info("GET /games");

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());

        Page<GameEntity> pagedGames = gameService.getAllGames(pageable);

        return new ResponseEntity<>(new PagedResponse(pagedGames.getTotalElements(), size, page, pagedGames.getContent()), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<GameEntity> getGameByUuid(@PathVariable String uuid) {
        log.info("GET /games/{uuid}");

        return new ResponseEntity<>(gameService.getGameByUuid(uuid), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GameEntity> createGame(@RequestBody AddGameRequest addGameRequest) {
        log.info("POST /games");

        return new ResponseEntity<>(gameService.createGame(addGameRequest), HttpStatus.OK);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<GameEntity> updateGameByUuid(@PathVariable String uuid, @RequestBody UpdateGameRequest game) {
        log.info("PUT /games/{uuid}");

        return new ResponseEntity<>(gameService.updateGame(game), HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> deleteGameByUuid(@PathVariable String uuid) {
        gameService.deleteGame(uuid);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }
}
