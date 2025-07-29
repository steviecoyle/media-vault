package com.scoyle.media_vault.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePublisherRequest {

    @NotNull(message = "Name must not be null")
    @Size(min = 10, message = "Name must be minimum 10 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 -]+$", message = "Name can only contain alphanumeric characters, spaces or hyphens")
    private String name;
}
