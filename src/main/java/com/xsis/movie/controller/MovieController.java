package com.xsis.movie.controller;

import com.github.fge.jsonpatch.JsonPatch;
import com.xsis.movie.dto.MovieRequest;
import com.xsis.movie.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping(path = "/Movies")
    public ResponseEntity<Object> movieList(){
        return movieService.getMovieList();
    }

    @GetMapping(path = "/Movies/{ID}")
    public ResponseEntity<Object> movieById(@PathVariable(value = "ID") Long id){

        return movieService.getMovieById(id);
    }

    @PostMapping(path = "/Movies")
    public ResponseEntity<Object> createMovie(@Valid @RequestBody MovieRequest movieRequest){
        return movieService.createMovie(movieRequest);
    }

    @PutMapping(path = "/Movies/{ID}")
    public ResponseEntity<Object> updateMovieById(@Valid @RequestBody MovieRequest request, @PathVariable(value = "ID") Long id){
        return movieService.updateMovieById(request, id);
    }

    @PatchMapping(path = "/Movies/{ID}")
    public ResponseEntity<Object> patchMovieById(@RequestBody JsonPatch jsonPatch, @PathVariable(value = "ID") Long id){
        return movieService.patchMovieById(jsonPatch, id);
    }

    @DeleteMapping(path = "/Movies/{ID}")
    public ResponseEntity<Object> deleteMovieById(@PathVariable(value = "ID") Long id){
        return movieService.deleteMovieById(id);
    }
}
