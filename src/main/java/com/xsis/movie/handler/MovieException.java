package com.xsis.movie.handler;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@NoArgsConstructor
public class MovieException extends RuntimeException {

    public MovieException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieException(String message) {
        super(message);
    }
}
