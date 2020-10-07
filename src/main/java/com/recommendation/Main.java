package com.recommendation;


import com.recommendation.model.Movie;
import com.recommendation.model.filter.DirectorFilter;
import com.recommendation.model.filter.MinutesFilter;
import com.recommendation.util.FilterUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    static FilterUtil filterUtil;

    public static void main(String[] args){

        problem1();




    }

    static void problem1(){

        HashMap<String,Movie> movies = MovieDB.getInstance().getMovies();
        int count = 0;
        for(Movie movie: movies.values()){
            if(MovieDB.getInstance().getRatingFrequence().get(movie.getId()) >= 5){
                count++;
            }
        }


        System.out.println("Count of movies: " + count);
        //movies.values().forEach(System.out::println);
    }

    static void problem10(){
        filterUtil = new FilterUtil(3,new DirectorFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"), new MinutesFilter(new int[]{90,180}));
        HashMap<String,Movie> movies = filterUtil.getMoviesByFilters(MovieDB.getInstance().getMovies());

        System.out.println("Count of movies: " + movies.size());
        movies.values().forEach(System.out::println);
    }





}
