package com.scoyle.media_vault.controller;

import com.scoyle.media_vault.persistence.entity.PublishersEntity;
import com.scoyle.media_vault.request.CreatePublisherRequest;
import com.scoyle.media_vault.request.UpdatePublisherRequest;
import com.scoyle.media_vault.service.PublisherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService service;

    @PostMapping
    public ResponseEntity<PublishersEntity> savePublisher(@RequestBody CreatePublisherRequest createPublisherRequest) {

        return new ResponseEntity<>(service.savePublisher(createPublisherRequest), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PublishersEntity> updatePublisher(@RequestBody UpdatePublisherRequest updatePublisherRequest) {

        return new ResponseEntity<>(service.updatePublisher(updatePublisherRequest), HttpStatus.OK);
    }
}
