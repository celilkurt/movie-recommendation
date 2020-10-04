package com.recommendation.model.filter;

import com.recommendation.model.Movie;

import java.util.ArrayList;

public class GenreFilter extends Filter<String>{


    public GenreFilter(String query) {
        super(query.toLowerCase());
    }

    @Override
    public ArrayList<Movie> getMovies(ArrayList<Movie> movies) {

        ArrayList<Movie> resultList = new ArrayList<>();

        for(Movie movie: movies){
            if(movie.getGenres().toLowerCase().contains(query)){
                resultList.add(movie);
            }
        }
        return resultList;
    }
}
