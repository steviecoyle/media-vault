package com.scoyle.media_vault.controller;

import com.scoyle.media_vault.persistence.entity.PublishersEntity;
import com.scoyle.media_vault.request.CreatePublisherRequest;
import com.scoyle.media_vault.request.UpdatePublisherRequest;
import com.scoyle.media_vault.response.PagedResponse;
import com.scoyle.media_vault.service.PublisherService;
import jakarta.validation.Valid;
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
@RequestMapping("/publishers")
public class PublisherController {

    private final PublisherService service;

    @GetMapping
    public ResponseEntity<PagedResponse> getAllPublishers(
            @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "20", required = false) Integer size,
            @RequestParam(name = "sortBy", defaultValue = "name", required = false) String sortBy) {

        log.info("GET /publishers");

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());

        Page<PublishersEntity> pagedPublishers = service.getAllPublishers(pageable);

        return new ResponseEntity<>(new PagedResponse(pagedPublishers.getTotalElements(), size,
                pagedPublishers.getNumber() + 1, pagedPublishers.getContent()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PublishersEntity> savePublisher(@Valid @RequestBody CreatePublisherRequest createPublisherRequest) {
        log.info("POST /publishers");

        return new ResponseEntity<>(service.savePublisher(createPublisherRequest), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<PublishersEntity> updatePublisher(
            @PathVariable String id,
            @RequestBody UpdatePublisherRequest updatePublisherRequest) {
        log.info("PUT /publishers");

        return new ResponseEntity<>(service.updatePublisher(id, updatePublisherRequest), HttpStatus.OK);
    }
}
