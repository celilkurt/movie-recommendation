package com.recommendation;


import com.recommendation.model.Movie;
import com.recommendation.model.filter.DirectorFilter;
import com.recommendation.model.filter.GenreFilter;
import com.recommendation.model.filter.MinutesFilter;
import com.recommendation.model.filter.YearsAfterFilter;
import com.recommendation.util.FilterUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    static FilterUtil filterUtil;

    public static void main(String[] args){

        problem10();




    }

    static void problem4(){

        HashMap<String,Movie> movies = MovieDB.getInstance().getMovies();
        int count = 0;
        for(Movie movie: movies.values()){
            if(MovieDB.getInstance().getRatings().get(movie.getId()).size() >= 35){
                count++;
            }
        }

        System.out.println("Count of movies: " + count);
        //movies.values().forEach(System.out::println);
    }

    static void problem5(){

        filterUtil = new FilterUtil(20,new YearsAfterFilter(2000));
        HashMap<String,Movie> movies = filterUtil.getMoviesByFilters(MovieDB.getInstance().getMovies());

        System.out.println("Count of movies: " + movies.size());
        movies.values().forEach(System.out::println);
    }

    static void problem6(){

        filterUtil = new FilterUtil(20,new GenreFilter("Comedy"));
        HashMap<String,Movie> movies = filterUtil.getMoviesByFilters(MovieDB.getInstance().getMovies());

        System.out.println("Count of movies: " + movies.size());
        movies.values().forEach(System.out::println);
    }

    static void problem7(){

        filterUtil = new FilterUtil(5,new MinutesFilter(new int[]{105,135}));
        HashMap<String,Movie> movies = filterUtil.getMoviesByFilters(MovieDB.getInstance().getMovies());

        System.out.println("Count of movies: " + movies.size());
        movies.values().forEach(System.out::println);
    }


    static void problem8(){

        filterUtil = new FilterUtil(4,new DirectorFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"));
        HashMap<String,Movie> movies = filterUtil.getMoviesByFilters(MovieDB.getInstance().getMovies());

        System.out.println("Count of movies: " + movies.size());
        movies.values().forEach(System.out::println);
    }

    static void problem9(){

        filterUtil = new FilterUtil(8,new YearsAfterFilter(1990),new GenreFilter("Drama"));
        HashMap<String,Movie> movies = filterUtil.getMoviesByFilters(MovieDB.getInstance().getMovies());

        System.out.println("Count of movies: " + movies.size());
        movies.values().forEach(System.out::println);
    }

    static void problem10(){
        filterUtil = new FilterUtil(3,new DirectorFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"), new MinutesFilter(new int[]{90,180}));
        HashMap<String,Movie> movies = filterUtil.getMoviesByFilters(MovieDB.getInstance().getMovies());

        System.out.println("Count of movies: " + movies.size());
        movies.values().forEach(System.out::println);
    }





}
