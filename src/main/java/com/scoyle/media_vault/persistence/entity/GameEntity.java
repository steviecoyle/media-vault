package com.scoyle.media_vault.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Games")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String title;

    @ManyToOne
    @JoinColumn(name = "developerId")
    private DevelopersEntity developer;

    @ManyToOne
    @JoinColumn(name = "publisherId")
    private PublishersEntity publisher;

    private Date releaseDate;
    private String platform;
    private String genre;
    private String description;
    private Double rating;
    private String coverArtLink;
}
