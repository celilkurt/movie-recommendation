package com.recommendation.model.filter;

import com.recommendation.MovieDB;
import com.recommendation.model.Movie;

import java.util.*;

public class DirectorFilter extends Filter<String[]> {

    public DirectorFilter(String query) {
        super(query.toLowerCase().split(","));
    }

    @Override
    public HashMap<String,Movie> getMoviesByFilter(HashMap<String,Movie> movies, int minRater) {

        HashMap<String,Movie> resultList = new HashMap<>();
        String[] queries = query;
        for(Map.Entry<String,Movie> movie: movies.entrySet()){
            if(MovieDB.getInstance().getRatingFrequence().get(movie.getKey()) >= minRater){
                for(String query: queries){
                    String[] directors = movie.getValue().getDirector().toLowerCase().split(",");
                    boolean key = false;
                    for(String director: directors){
                        if(director.equals(query)){
                            resultList.put(movie.getKey(),movie.getValue());
                            break;
                        }
                    }
                    if(key){
                        break;
                    }
                }
            }

        }
        return resultList;
    }

}
