package com.recommendation.model.filter;

import com.recommendation.MovieDB;
import com.recommendation.model.Avarage;
import com.recommendation.model.Movie;
import com.recommendation.model.Rating;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Filter<Q> {

    Q query;
    public Filter(Q query){ this.query = query;}

    public abstract ArrayList<Movie> getMoviesByFilter(ArrayList<Movie> movies,int minRater);


}
