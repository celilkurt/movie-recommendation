package com.recommendation.filter;

import com.recommendation.MovieDB;
import com.recommendation.model.Movie;


import java.util.*;

public class YearsAfterFilter extends Filter<Integer> {

    public YearsAfterFilter(int query) { super(query);  }

    @Override
    public HashMap<String,Movie> getMoviesByFilter(HashMap<String,Movie> movies, int minRater) {

        HashMap<String,Movie>  resultList = new HashMap<>();
        for(Map.Entry<String,Movie> entry: movies.entrySet()){
            if(MovieDB.getInstance().getRatings().get(entry.getKey()).size() >= minRater){
                if(entry.getValue().getYear() >= query){
                    resultList.put(entry.getKey(),entry.getValue());
                }
            }
        }

        return resultList;

    }


}
