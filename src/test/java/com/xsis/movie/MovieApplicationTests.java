package com.xsis.movie;

import com.xsis.movie.dto.MovieResponse;
import com.xsis.movie.model.Movie;
import com.xsis.movie.service.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class MovieApplicationTests {
	MovieService movieService;

	MockMvc mockMvc;

	@Test
	public void testGetMovieById() throws Exception {
		Long movieId = 1L;
		Movie movie = new Movie(movieId, "Movie 1", "Description Movie 1", 7F, "image.jpg", new Date(), new Date());
		Optional<Movie> optionalMovie = Optional.of(movie);

		Mockito.when(movieService.getMovieById(movieId))
				.thenReturn(MovieResponse.generateResponse("successfully get movie", HttpStatus.OK, optionalMovie.get()));

		mockMvc.perform(MockMvcRequestBuilders.get("/Movies/:ID", movieId))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json("{}")); // JSON representation of movie
	}

	@Test
	void contextLoads() {
	}

}
