package com.recommendation.model.filter;

import com.recommendation.model.Avarage;
import com.recommendation.model.Movie;
import com.recommendation.model.Rating;

import java.util.ArrayList;

public class GenreFilter extends Filter<String>{


    public GenreFilter(String query) {
        super(query.toLowerCase());
    }

    @Override
    public ArrayList<Movie> getMoviesByFilter(ArrayList<Movie> movies) {

        ArrayList<Movie> resultList = new ArrayList<>();

        for(Movie movie: movies){
            if(movie.getGenres().toLowerCase().contains(query)){
                resultList.add(movie);
            }
        }
        return resultList;
    }


}
