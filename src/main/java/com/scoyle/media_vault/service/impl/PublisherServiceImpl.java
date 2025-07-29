package com.scoyle.media_vault.service.impl;

import com.scoyle.media_vault.exception.ResourceNotFoundException;
import com.scoyle.media_vault.persistence.entity.PublishersEntity;
import com.scoyle.media_vault.persistence.repository.PublisherRepository;
import com.scoyle.media_vault.request.CreatePublisherRequest;
import com.scoyle.media_vault.request.UpdatePublisherRequest;
import com.scoyle.media_vault.service.PublisherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository repository;

    @Override
    public Page<PublishersEntity> getAllPublishers(Pageable pageable) {

        return repository.findAll(pageable);
    }

    @Override
    public PublishersEntity savePublisher(CreatePublisherRequest request) {
        PublishersEntity entity = new PublishersEntity();
        entity.setName(request.getName());

        return repository.save(entity);
    }

    @Override
    public PublishersEntity updatePublisher(String id, UpdatePublisherRequest updatePublisherRequest) {
        try {
            Long publisherId = Long.parseLong(id);
            Optional<PublishersEntity> optionalPublisher = repository.findById(publisherId);

            if (optionalPublisher.isEmpty()) {
                log.error("Publisher with Id [{}] not found", publisherId);
                throw new ResourceNotFoundException("Publisher with Id [" + publisherId + "] not found");
            }

            PublishersEntity publisherToUpdate = optionalPublisher.get();
            publisherToUpdate.setName(updatePublisherRequest.getName());
            publisherToUpdate.setLastUpdated(null);

            return repository.save(publisherToUpdate);

        } catch (NumberFormatException nfe) {
            log.error("Invalid Publisher Id - {}", nfe.getMessage());
            throw new IllegalArgumentException("Invalid Publisher Id");
        }
    }
}
