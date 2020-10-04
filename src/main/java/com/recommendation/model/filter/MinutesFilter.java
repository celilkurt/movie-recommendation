package com.recommendation.model.filter;

import com.recommendation.model.Movie;

import java.util.ArrayList;

public class MinutesFilter extends Filter<int[]>{

    public MinutesFilter(int[] query) {
        super(query);
    }

    @Override
    public ArrayList<Movie> getMovies(ArrayList<Movie> movies) {

        ArrayList<Movie> resultList = new ArrayList<>();
        for(Movie movie: movies){
            if(movie.getMinutes() > query[0] && movie.getMinutes() < query[1]){
                resultList.add(movie);
            }
        }
        return resultList;
    }
}
