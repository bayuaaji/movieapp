package com.xsis.movie.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {

    @NotBlank(message = "Title should not be blank")
    private String title;

    @Size(min=5, message = "Description should have at least 5 characters")
    private String description;

    private Float rating;

    private String image;

}
