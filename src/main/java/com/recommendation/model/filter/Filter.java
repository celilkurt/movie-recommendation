package com.recommendation.model.filter;

import com.recommendation.model.Movie;
import com.recommendation.model.Rating;

import java.util.ArrayList;
import java.util.List;

public abstract class Filter<Q> {

    Q query;
    public Filter(Q query){ this.query = query;}

    public abstract ArrayList<Movie> getMovies(ArrayList<Movie> movies);

}
