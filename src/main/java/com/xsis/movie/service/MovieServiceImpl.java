package com.xsis.movie.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.xsis.movie.dto.MovieRequest;
import com.xsis.movie.dto.MovieResponse;
import com.xsis.movie.handler.MovieException;
import com.xsis.movie.model.Movie;
import com.xsis.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Override
    public ResponseEntity<Object> getMovieList() {
        try {
            var result = movieRepository.findAll();
            if(result.isEmpty()){
                throw new MovieException("Movie is empty!");
            }

            return MovieResponse.generateResponse("successfully get all movies", HttpStatus.OK, result);
        } catch (MovieException e){
            return MovieResponse.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    @Override
    public ResponseEntity<Object> getMovieById(Long id) {
        try {
            Optional<Movie> result = movieRepository.findById(id);
            if(result.isEmpty()){
                throw new MovieException("Movie not found with id: " + id);
            }

            return MovieResponse.generateResponse("successfully get movie", HttpStatus.OK, result.get());
        } catch (MovieException e){
            return MovieResponse.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    @Override
    public ResponseEntity<Object> createMovie(MovieRequest request) {
        try {
            Movie movie = new Movie();

            movie.setTitle(request.getTitle());
            movie.setDescription(request.getDescription());
            movie.setRating(request.getRating());
            movie.setImage(request.getImage());
            movie.setCreatedAt(new Date());
            movie.setUpdatedAt(null);

            Movie result = movieRepository.save(movie);
            if(result == null){
                throw new MovieException("Internal Server Error");
            }

            return MovieResponse.generateResponse("successfully created movie", HttpStatus.CREATED, result);
        } catch (MovieException e){
            return MovieResponse.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @Override
    public ResponseEntity<Object> updateMovieById(MovieRequest request, Long id) {
        try {
            Movie movie = movieRepository.findById(id).orElseThrow(() -> new MovieException("Movie not found with id: " + id));
            movie.setTitle(request.getTitle());
            movie.setDescription(request.getDescription());
            movie.setRating(request.getRating());
            movie.setImage(request.getImage());
            movie.setUpdatedAt(new Date());

            Movie saveMovie = movieRepository.save(movie);

            return MovieResponse.generateResponse("successfully updated movie", HttpStatus.CREATED, saveMovie);
        } catch (MovieException e){
            return MovieResponse.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);

        }
    }

    public ResponseEntity<Object> patchMovieById(JsonPatch request, Long id){
        try {
            Movie movie = movieRepository.findById(id).orElseThrow(() -> new MovieException("Movie not found with id: " + id));
            Movie moviePatched = applyPatchToCustomer(request, movie);
            moviePatched.setUpdatedAt(new Date());

            Movie saveMovie = movieRepository.save(moviePatched);

            return MovieResponse.generateResponse("successfully patched movie", HttpStatus.CREATED, saveMovie);
        } catch (MovieException e){
            return MovieResponse.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);

        } catch (JsonPatchException e) {
            return MovieResponse.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public ResponseEntity<Object> deleteMovieById(Long id) {
        try {
            movieRepository.findById(id).orElseThrow(() -> new MovieException("Movie not found with id: " + id));
            movieRepository.deleteById(id);

            return MovieResponse.generateResponse("successfully deleted movie", HttpStatus.OK, null);
        } catch (MovieException e){
            return MovieResponse.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);

        }
    }

    private Movie applyPatchToCustomer(
            JsonPatch patch, Movie targetCustomer) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(targetCustomer, JsonNode.class));
        return objectMapper.treeToValue(patched, Movie.class);
    }
}
