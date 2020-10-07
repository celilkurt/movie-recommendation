package com.recommendation.model.filter;

import com.recommendation.MovieDB;
import com.recommendation.model.Avarage;
import com.recommendation.model.Movie;
import com.recommendation.model.Rating;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public abstract class Filter<Q> {

    Q query;
    public Filter(Q query){ this.query = query;}

    public abstract HashMap<String,Movie> getMoviesByFilter(HashMap<String,Movie> movies, int minRater);


}
