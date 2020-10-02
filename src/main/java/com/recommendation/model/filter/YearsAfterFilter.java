package com.recommendation.model.filter;

import com.recommendation.model.Movie;
import com.recommendation.model.Rater;
import com.recommendation.model.Rating;


import java.util.ArrayList;
import java.util.List;

public class YearsAfterFilter implements Filter<Integer> {


    @Override
    public ArrayList<Movie> getMovies(Integer query, ArrayList<Movie> movies) {

        ArrayList<Movie> resultList = new ArrayList<>();
        for(Movie movie: movies){
            if(movie.getYear() > query){
                resultList.add(movie);
            }
        }

        return resultList;

    }

    @Override
    public Long getAvarageRatesByFilter(Integer query, ArrayList<Movie> movies, ArrayList<Rating> ratings) {

        ArrayList<Movie> filterResult = getMovies(query,movies);
        Long sum = 0L;
        for(Movie movie:filterResult){

        }

        return null;
    }
}
