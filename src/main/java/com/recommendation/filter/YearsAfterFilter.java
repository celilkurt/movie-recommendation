package com.recommendation.filter;

import com.recommendation.Database;
import com.recommendation.model.Movie;


import java.util.*;

public class YearsAfterFilter extends Filter<Integer> {

    public YearsAfterFilter(int query) { super(query);  }

    @Override
    public HashMap<String,Movie> getMoviesByFilter(HashMap<String,Movie> movies, int minRater) {

        HashMap<String,Movie>  resultList = new HashMap<>();
        for(Map.Entry<String,Movie> entry: movies.entrySet()){
            if(Database.getInstance().getRatings().get(entry.getKey()).size() >= minRater){
                if(isMatch(entry.getKey())){
                    resultList.put(entry.getKey(),entry.getValue());
                }
            }
        }

        return resultList;

    }

    @Override
    public boolean isMatch(String movieID) {
        if(Database.getInstance().getMovies().get(movieID).getYear() >= query){
            return true;
        }
        return false;
    }


}
