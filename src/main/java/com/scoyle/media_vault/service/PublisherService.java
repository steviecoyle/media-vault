package com.scoyle.media_vault.service;

import com.scoyle.media_vault.persistence.entity.PublishersEntity;
import com.scoyle.media_vault.request.CreatePublisherRequest;
import com.scoyle.media_vault.request.UpdatePublisherRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PublisherService {

    Page<PublishersEntity> getAllPublishers(Pageable pageable);

    PublishersEntity savePublisher(CreatePublisherRequest request);

    PublishersEntity updatePublisher(String id, UpdatePublisherRequest updatePublisherRequest);
}
