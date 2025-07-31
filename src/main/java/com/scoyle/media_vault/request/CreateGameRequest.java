package com.scoyle.media_vault.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateGameRequest {

    @NotNull(message = "Title must not be null")
    @Size(min = 5, message = "Title must be minimum 5 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 -]+$", message = "Title can only contain alphanumeric characters, spaces or hyphens")
    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;
    
    private String platform;
    private String genre;
    private String description;
    private double rating;
    private String coverArt;

    @NotNull(message = "Publisher Id must not be null")
    private Long publisherId;

    @NotNull(message = "Developer Id must not be null")
    private Long developerId;
}
