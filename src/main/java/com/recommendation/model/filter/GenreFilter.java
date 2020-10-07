package com.recommendation.model.filter;

import com.recommendation.MovieDB;
import com.recommendation.model.Movie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GenreFilter extends Filter<String>{


    public GenreFilter(String query) {
        super(query.toLowerCase());
    }

    @Override
    public HashMap<String,Movie> getMoviesByFilter(HashMap<String,Movie> movies, int minRater) {

        HashMap<String,Movie>  resultList = new HashMap<>() ;

        for(Map.Entry<String,Movie> movie: movies.entrySet()){
            if(MovieDB.getInstance().getRatingFrequence().get(movie.getKey()) >= minRater){
                if(movie.getValue().getGenres().toLowerCase().contains(query)){
                    resultList.put(movie.getKey(),movie.getValue());
                }
            }

        }
        return resultList;
    }


}
