package com.scoyle.media_vault.service;

import com.scoyle.media_vault.persistence.entity.PublishersEntity;
import com.scoyle.media_vault.request.CreatePublisherRequest;
import com.scoyle.media_vault.request.UpdatePublisherRequest;

public interface PublisherService {

    PublishersEntity savePublisher(CreatePublisherRequest request);

    PublishersEntity updatePublisher(UpdatePublisherRequest updatePublisherRequest);
}
