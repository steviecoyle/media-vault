package com.scoyle.media_vault.service.impl;

import com.scoyle.media_vault.persistence.entity.GameEntity;
import com.scoyle.media_vault.persistence.repository.GameRepository;
import com.scoyle.media_vault.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Log4j2
@RequiredArgsConstructor
@Service
class GamesServiceImpl implements GameService {

    private final GameRepository repository;

    @Override
    public Page<GameEntity> getAllGames(Pageable pageable) {

        return repository.findAll(pageable);
    }
}
