package com.recommendation.model.filter;

import com.recommendation.model.Movie;

import java.util.ArrayList;

public class DirectorFilter extends Filter<String> {

    public DirectorFilter(String query) {
        super(query.toLowerCase());
    }

    @Override
    public ArrayList<Movie> getMovies(ArrayList<Movie> movies) {

        ArrayList<Movie> resultList = new ArrayList<>();

        for(Movie movie: movies){
            if(movie.getDirector().toLowerCase().contains(query)){
                resultList.add(movie);
            }
        }
        return resultList;
    }
}
