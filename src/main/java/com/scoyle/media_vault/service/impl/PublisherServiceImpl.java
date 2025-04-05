package com.scoyle.media_vault.service.impl;

import com.scoyle.media_vault.persistence.entity.PublishersEntity;
import com.scoyle.media_vault.persistence.repository.PublisherRepository;
import com.scoyle.media_vault.request.CreatePublisherRequest;
import com.scoyle.media_vault.request.UpdatePublisherRequest;
import com.scoyle.media_vault.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository repository;

    @Override
    public PublishersEntity savePublisher(CreatePublisherRequest request) {
        PublishersEntity entity = new PublishersEntity();
        entity.setName(request.getName());

        return repository.save(entity);
    }

    @Override
    public PublishersEntity updatePublisher(UpdatePublisherRequest updatePublisherRequest) {
        Optional<PublishersEntity> optionalPublisher = repository.findById(updatePublisherRequest.getId());

        if (optionalPublisher.isPresent()) {
            PublishersEntity publisherToUpdate = optionalPublisher.get();
            publisherToUpdate.setName(updatePublisherRequest.getName());
            publisherToUpdate.setLastUpdated(null);

            return repository.save(publisherToUpdate);
        } else {
            return null;
        }
    }
}
