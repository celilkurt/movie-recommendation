package com.recommendation.model.filter;

import com.recommendation.MovieDB;
import com.recommendation.model.Avarage;
import com.recommendation.model.Movie;
import com.recommendation.model.Rating;

import java.util.ArrayList;

public class MinutesFilter extends Filter<int[]>{

    public MinutesFilter(int[] query) {
        super(query);
    }

    @Override
    public ArrayList<Movie> getMoviesByFilter(ArrayList<Movie> movies, int minRater) {

        ArrayList<Movie> resultList = new ArrayList<>();
        for(Movie movie: movies){
            if(MovieDB.getInstance().getRatingFrequence().get(movie.getId()) >= minRater){
                if(movie.getMinutes() >= query[0] && movie.getMinutes() <= query[1]){
                    resultList.add(movie);
                }
            }

        }
        return resultList;
    }


}
