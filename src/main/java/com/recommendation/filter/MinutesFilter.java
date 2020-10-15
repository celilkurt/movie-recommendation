package com.recommendation.filter;

import com.recommendation.Database;
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
        Movie movie = Database.getInstance().getMovies().get(movieID);
        if(movie.getMinutes() >= query[0] && movie.getMinutes() <= query[1]){
            return true;
        }
        return false;
    }


}
