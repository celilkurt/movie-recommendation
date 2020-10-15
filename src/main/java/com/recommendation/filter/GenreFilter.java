package com.recommendation.filter;

import com.recommendation.Database;
import com.recommendation.model.Movie;

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
            if(Database.getInstance().getRatings().get(movie.getKey()).size() >= minRater){
                if(isMatch(movie.getKey())){
                    resultList.put(movie.getKey(),movie.getValue());
                }
            }

        }
        return resultList;
    }

    @Override
    public boolean isMatch(String movieID) {
        if(Database.getInstance().getMovies().get(movieID).getGenres().toLowerCase().contains(query)){
            return true;
        }
        return false;
    }


}
