package com.recommendation.model.filter;

import com.recommendation.model.Avarage;
import com.recommendation.model.Movie;
import com.recommendation.model.Rating;

import java.util.ArrayList;
import java.util.Collections;

public class DirectorFilter extends Filter<String[]> {

    public DirectorFilter(String query) {
        super(query.toLowerCase().split(","));
    }

    @Override
    public ArrayList<Movie> getMoviesByFilter(ArrayList<Movie> movies) {

        ArrayList<Movie> resultList = new ArrayList<>();
        String[] queries = query;
        for(Movie movie: movies){
            for(String query: queries){
                if(movie.getDirector().toLowerCase().contains(query)){
                    resultList.add(movie);
                    break;
                }
            }
        }
        return resultList;
    }

}
