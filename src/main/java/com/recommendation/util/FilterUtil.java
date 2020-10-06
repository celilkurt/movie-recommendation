package com.recommendation.util;

import com.recommendation.model.Avarage;
import com.recommendation.model.Movie;
import com.recommendation.model.Rating;
import com.recommendation.model.filter.Filter;

import java.util.ArrayList;
import java.util.Collections;

public class FilterUtil {

    private ArrayList<Filter> filters;

    public FilterUtil(Filter... filters){
        this.filters = new ArrayList<Filter>();
        for(Filter filter: filters){
            this.filters.add(filter);
        }
    }

    public ArrayList<Movie> getMoviesByFilters(ArrayList<Movie> movies, ArrayList<Rating> ratings){
        for(Filter filter: filters){
            movies = filter.getMoviesByFilter(movies);
        }
        return movies;
    }

    public ArrayList<Avarage> getAvaragesByFilters(ArrayList<Movie> movies, ArrayList<Rating> ratings){

        movies = getMoviesByFilters(movies, ratings);
        return getAvarages(movies,ratings);
    }

    public ArrayList<Avarage> getAvarages(ArrayList<Movie> movies, ArrayList<Rating> ratings) {


        ArrayList<Avarage> avarages = new ArrayList<>();
        for(Movie movie: movies){
            Avarage avarage = new Avarage(movie.getId(), movie);
            double sum = 0;
            int count = 0;

            for(Rating rating: ratings){
                if(rating.getItem().equals(movie.getId())){
                    count++;
                    sum += rating.getValue();
                }
            }
            avarage.setAvarage(sum/count);
            avarages.add(avarage);
        }
        Collections.sort(avarages);

        return avarages;
    }
}
