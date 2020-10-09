package com.recommendation.filter;

import com.recommendation.model.Movie;

import java.util.HashMap;

public abstract class Filter<Q> {

    Q query;
    public Filter(Q query){ this.query = query;}

    public abstract HashMap<String,Movie> getMoviesByFilter(HashMap<String,Movie> movies, int minRater);


}
