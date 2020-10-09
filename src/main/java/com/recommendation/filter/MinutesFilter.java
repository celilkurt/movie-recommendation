package com.recommendation.filter;

import com.recommendation.MovieDB;
import com.recommendation.model.Movie;

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
            if(MovieDB.getInstance().getRatings().get(movie.getKey()).size() >= minRater){
                if(movie.getValue().getMinutes() >= query[0] && movie.getValue().getMinutes() <= query[1]){
                    resultList.put(movie.getKey(),movie.getValue());
                }
            }

        }
        return resultList;
    }


}
