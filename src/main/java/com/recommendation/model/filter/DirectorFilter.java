package com.recommendation.model.filter;

import com.recommendation.MovieDB;
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
    public ArrayList<Movie> getMoviesByFilter(ArrayList<Movie> movies, int minRater) {

        ArrayList<Movie> resultList = new ArrayList<>();
        String[] queries = query;
        for(Movie movie: movies){
            if(MovieDB.getInstance().getRatingFrequence().get(movie.getId()) >= minRater){
                for(String query: queries){
                    String[] directors = movie.getDirector().toLowerCase().split(",");
                    boolean key = false;
                    for(String director: directors){
                        if(director.equals(query)){
                            resultList.add(movie);
                            break;
                        }
                    }
                    if(key){
                        break;
                    }
                }
            }

        }
        return resultList;
    }

}
