package com.recommendation.model.filter;

import com.recommendation.MovieDB;
import com.recommendation.model.Avarage;
import com.recommendation.model.Movie;
import com.recommendation.model.Rater;
import com.recommendation.model.Rating;


import java.util.*;

public class YearsAfterFilter extends Filter<Integer> {

    public YearsAfterFilter(int query) { super(query);  }

    @Override
    public HashMap<String,Movie> getMoviesByFilter(HashMap<String,Movie> movies, int minRater) {

        HashMap<String,Movie>  resultList = new HashMap<>();
        for(Map.Entry<String,Movie> entry: movies.entrySet()){
            if(MovieDB.getInstance().getRatingFrequence().get(entry.getKey()) >= minRater){
                if(entry.getValue().getYear() >= query){
                    resultList.put(entry.getKey(),entry.getValue());
                }
            }
        }

        return resultList;

    }


}
