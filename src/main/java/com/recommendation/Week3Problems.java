package com.recommendation;

import com.recommendation.filter.DirectorFilter;
import com.recommendation.filter.GenreFilter;
import com.recommendation.filter.MinutesFilter;
import com.recommendation.filter.YearsAfterFilter;
import com.recommendation.model.Movie;
import com.recommendation.util.FilterUtil;

import java.util.HashMap;

public class Week3Problems {
    private FilterUtil filterUtil;

    void problem3_4(){

        HashMap<String, Movie> movies = Database.getInstance().getMovies();
        int count = 0;
        for(Movie movie: movies.values()){
            if(Database.getInstance().getRatings().get(movie.getId()).size() >= 35){
                count++;
            }
        }

        System.out.println("Count of movies: " + count);
        //movies.values().forEach(System.out::println);
    }

    void problem3_5(){

        filterUtil = new FilterUtil(20,new YearsAfterFilter(2000));
        HashMap<String,Movie> movies = filterUtil.getMoviesByFilters(Database.getInstance().getMovies());

        System.out.println("Count of movies: " + movies.size());
        movies.values().forEach(System.out::println);
    }

    void problem3_6(){

        filterUtil = new FilterUtil(20,new GenreFilter("Comedy"));
        HashMap<String,Movie> movies = filterUtil.getMoviesByFilters(Database.getInstance().getMovies());

        System.out.println("Count of movies: " + movies.size());
        movies.values().forEach(System.out::println);
    }

    void problem3_7(){

        filterUtil = new FilterUtil(5,new MinutesFilter(new int[]{105,135}));
        HashMap<String,Movie> movies = filterUtil.getMoviesByFilters(Database.getInstance().getMovies());

        System.out.println("Count of movies: " + movies.size());
        movies.values().forEach(System.out::println);
    }


    void problem3_8(){

        filterUtil = new FilterUtil(4,new DirectorFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"));
        HashMap<String,Movie> movies = filterUtil.getMoviesByFilters(Database.getInstance().getMovies());

        System.out.println("Count of movies: " + movies.size());
        movies.values().forEach(System.out::println);
    }

    void problem3_9(){

        filterUtil = new FilterUtil(8,new YearsAfterFilter(1990),new GenreFilter("Drama"));
        HashMap<String,Movie> movies = filterUtil.getMoviesByFilters(Database.getInstance().getMovies());

        System.out.println("Count of movies: " + movies.size());
        movies.values().forEach(System.out::println);
    }

    void problem3_10(){
        filterUtil = new FilterUtil(3,new DirectorFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"), new MinutesFilter(new int[]{90,180}));
        HashMap<String,Movie> movies = filterUtil.getMoviesByFilters(Database.getInstance().getMovies());

        System.out.println("Count of movies: " + movies.size());
        movies.values().forEach(System.out::println);
    }

}
