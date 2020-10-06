package com.recommendation.util;

import com.recommendation.MovieDB;
import com.recommendation.model.Avarage;
import com.recommendation.model.Movie;
import com.recommendation.model.Rating;
import com.recommendation.model.filter.Filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FilterUtil {

    private ArrayList<Filter> filters;
    private int minRater = 0;

    public FilterUtil(int minRater, Filter... filters){
        this.minRater = minRater;
        this.filters = new ArrayList<Filter>();
        this.filters.addAll(Arrays.asList(filters));
    }

    public ArrayList<Movie> getMoviesByFilters(ArrayList<Movie> movies, ArrayList<Rating> ratings){
        for(Filter filter: filters){
            movies = filter.getMoviesByFilter(movies,minRater);
            ArrayList<Movie> tempList = new ArrayList<>();
            for(Movie movie: movies){
                if(MovieDB.getRatingFrequence().get(movie.getId()) >= minRater){
                    tempList.add(movie);
                }
            }
            movies = tempList;
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
