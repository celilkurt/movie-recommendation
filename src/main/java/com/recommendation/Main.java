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

    private static ArrayList<Movie> movies;
    private static ArrayList<Rater> raters;
    private static ArrayList<Rating> ratings;

    public static void main(String[] args){


        FilterUtil filterUtil = new FilterUtil(new MinutesFilter(new int[]{90,180}),new DirectorFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        filterUtil.getMoviesByFilters(MovieDB.getInstance().getMovies(), MovieDB.getInstance().getRatings());

        System.out.println("Count of movies: " + movies.size());
        movies.forEach(System.out::println);

    }





}
