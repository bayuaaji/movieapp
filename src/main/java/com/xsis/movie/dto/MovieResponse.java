package com.xsis.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class MovieResponse {

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object object){
        Map<String,Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status);
        map.put("data", object);

        return new ResponseEntity<Object>(map, status);
    }
}

