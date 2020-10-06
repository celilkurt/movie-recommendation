package com.recommendation.model.filter;

import com.recommendation.MovieDB;
import com.recommendation.model.Avarage;
import com.recommendation.model.Movie;
import com.recommendation.model.Rater;
import com.recommendation.model.Rating;


import java.util.ArrayList;
import java.util.Collections;

public class YearsAfterFilter extends Filter<Integer> {

    public YearsAfterFilter(int query) { super(query);  }

    @Override
    public ArrayList<Movie> getMoviesByFilter( ArrayList<Movie> movies, int minRater) {

        ArrayList<Movie> resultList = new ArrayList<>();
        for(Movie movie: movies){
            if(MovieDB.getInstance().getRatingFrequence().get(movie.getId()) >= minRater){
                if(movie.getYear() >= query){
                    resultList.add(movie);
                }
            }
        }

        return resultList;

    }


}
