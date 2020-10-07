package com.recommendation.model.filter;

import com.recommendation.MovieDB;
import com.recommendation.model.Avarage;
import com.recommendation.model.Movie;
import com.recommendation.model.Rating;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MinutesFilter extends Filter<int[]>{

    public MinutesFilter(int[] query) {
        super(query);
    }

    @Override
    public HashMap<String,Movie> getMoviesByFilter(HashMap<String,Movie> movies, int minRater) {

        HashMap<String,Movie> resultList = new HashMap<>();
        for(Map.Entry<String,Movie> movie: movies.entrySet()){
            if(MovieDB.getInstance().getRatingFrequence().get(movie.getKey()) >= minRater){
                if(movie.getValue().getMinutes() >= query[0] && movie.getValue().getMinutes() <= query[1]){
                    resultList.put(movie.getKey(),movie.getValue());
                }
            }

        }
        return resultList;
    }


}
