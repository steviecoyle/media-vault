package com.scoyle.media_vault.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddGameRequest {

    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;
    private String platform;
    private String genre;
    private String description;
    private double rating;
    private String coverArt;
    private Long publisherId;
    private Long developerId;
}
