package com.scoyle.media_vault.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UpdateGameRequest {

    private Long id;
    private String title;
    private String developer;
    private String publisher;
    private Date releaseDate;
    private String platform;
    private String genre;
    private String description;
    private double rating;
    private String coverArt;
}
