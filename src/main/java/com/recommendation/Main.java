package com.recommendation;

import com.recommendation.model.Avarage;
import com.recommendation.model.Movie;
import com.recommendation.model.Rater;
import com.recommendation.model.Rating;
import com.recommendation.model.filter.DirectorFilter;
import com.recommendation.model.filter.GenreFilter;
import com.recommendation.model.filter.MinutesFilter;
import com.recommendation.model.filter.YearsAfterFilter;
import com.recommendation.util.FilterUtil;

import java.util.ArrayList;

public class Main {


    public static void main(String[] args){

        FilterUtil filterUtil = new FilterUtil(3,new DirectorFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"), new MinutesFilter(new int[]{90,180}));
        ArrayList<Movie> movies = filterUtil.getMoviesByFilters(MovieDB.getInstance().getMovies(), MovieDB.getInstance().getRatings());

        System.out.println("Count of movies: " + movies.size());
        movies.forEach(System.out::println);

    }





}
