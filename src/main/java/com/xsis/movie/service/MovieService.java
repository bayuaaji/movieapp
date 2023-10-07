package com.xsis.movie.service;

import com.github.fge.jsonpatch.JsonPatch;
import com.xsis.movie.dto.MovieRequest;
import org.springframework.http.ResponseEntity;

public interface MovieService {
    ResponseEntity<Object> getMovieList();

    ResponseEntity<Object> getMovieById(Long id);

    ResponseEntity<Object> createMovie(MovieRequest movie);

    ResponseEntity<Object> updateMovieById(MovieRequest request, Long id);

    ResponseEntity<Object> deleteMovieById(Long id);

    ResponseEntity<Object> patchMovieById(JsonPatch jsonPatch, Long id);
}
