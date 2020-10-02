package com.recommendation.model.filter;

import com.recommendation.model.Movie;
import com.recommendation.model.Rating;

import java.util.ArrayList;
import java.util.List;

public interface Filter<Q> {

    List<Movie> getMovies(Q query, ArrayList<Movie> movies);

    Long getAvarageRatesByFilter(Q query, ArrayList<Movie> movies, ArrayList<Rating> ratings);
}
