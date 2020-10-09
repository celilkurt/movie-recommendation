package com.recommendation.filter;

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
        for(Movie movie: movies.values()){
            if(MovieDB.getInstance().getRatings().get(movie.getId()).size() >= minRater){
                boolean key = false;
                String[] directors = movie.getDirector().toLowerCase().split(",");
                for(String query: queries){
                    for(String director: directors){
                        if(director.equals(query)){
                            resultList.put(movie.getId(),movie);
                            key = true;
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
