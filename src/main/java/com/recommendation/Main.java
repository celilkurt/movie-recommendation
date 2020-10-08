package com.recommendation;


import com.recommendation.model.Movie;
import com.recommendation.model.Rater;
import com.recommendation.model.Rating;
import com.recommendation.model.filter.DirectorFilter;
import com.recommendation.model.filter.GenreFilter;
import com.recommendation.model.filter.MinutesFilter;
import com.recommendation.model.filter.YearsAfterFilter;
import com.recommendation.util.FilterUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static FilterUtil filterUtil;

    public static void main(String[] args){

        HashMap<String,Double> closenesses = getWeightedAvarages("735");
        closenesses.remove("735");
        String maxId = "";

        for(Map.Entry<String,Double> closeness: closenesses.entrySet()){
            if(maxId.isEmpty()){
                maxId = closeness.getKey();
            }

            if(closenesses.get(maxId) < closeness.getValue()){
                maxId = closeness.getKey();
            }

        }

        System.out.println(maxId + ": " + closenesses.get(maxId));
        for(Rating r1 :MovieDB.getInstance().getRaters().get(maxId).getMyRatings()){
            for (Rating r2: MovieDB.getInstance().getRaters().get("735").getMyRatings()){
                if(r1.getItem().equals(r2.getItem())){
                    System.out.println(r1 + "  :  " + r2);
                    break;
                }
            }
        }

        closenesses.entrySet().forEach((Map.Entry<String,Double> entry) ->{
            System.out.println(entry.getKey() + ": " + entry.getValue());
        });


    }

    public static HashMap<String, Double> getWeightedAvarages(String id){

        HashMap<String, Rater> raters = MovieDB.getInstance().getRaters();
        Rater rater;

        if(raters.containsKey(id)){
            rater = raters.get(id);
        }else{
            return null;
        }

        HashMap<String,Double> closenessMap = new HashMap<>();

        for(Rater tempRater: raters.values()){
            closenessMap.put(tempRater.getID(),findCloseness(rater,tempRater));
        }

        return closenessMap;
    }

    public static double findCloseness(Rater r1, Rater r2){

        double closeness = 0;
        for(Rating r1Rating:r1.getMyRatings()){
            for(Rating r2Rating: r2.getMyRatings()){
                if(r1Rating.getItem().equals(r2Rating.getItem())){
                    closeness += (r1Rating.getValue() - 5)*(r2Rating.getValue() - 5);
                    break;
                }
            }
        }
        return closeness;
    }

    static void problem3_4(){

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

    static void problem3_5(){

        filterUtil = new FilterUtil(20,new YearsAfterFilter(2000));
        HashMap<String,Movie> movies = filterUtil.getMoviesByFilters(MovieDB.getInstance().getMovies());

        System.out.println("Count of movies: " + movies.size());
        movies.values().forEach(System.out::println);
    }

    static void problem3_6(){

        filterUtil = new FilterUtil(20,new GenreFilter("Comedy"));
        HashMap<String,Movie> movies = filterUtil.getMoviesByFilters(MovieDB.getInstance().getMovies());

        System.out.println("Count of movies: " + movies.size());
        movies.values().forEach(System.out::println);
    }

    static void problem3_7(){

        filterUtil = new FilterUtil(5,new MinutesFilter(new int[]{105,135}));
        HashMap<String,Movie> movies = filterUtil.getMoviesByFilters(MovieDB.getInstance().getMovies());

        System.out.println("Count of movies: " + movies.size());
        movies.values().forEach(System.out::println);
    }


    static void problem3_8(){

        filterUtil = new FilterUtil(4,new DirectorFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"));
        HashMap<String,Movie> movies = filterUtil.getMoviesByFilters(MovieDB.getInstance().getMovies());

        System.out.println("Count of movies: " + movies.size());
        movies.values().forEach(System.out::println);
    }

    static void problem3_9(){

        filterUtil = new FilterUtil(8,new YearsAfterFilter(1990),new GenreFilter("Drama"));
        HashMap<String,Movie> movies = filterUtil.getMoviesByFilters(MovieDB.getInstance().getMovies());

        System.out.println("Count of movies: " + movies.size());
        movies.values().forEach(System.out::println);
    }

    static void problem3_10(){
        filterUtil = new FilterUtil(3,new DirectorFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"), new MinutesFilter(new int[]{90,180}));
        HashMap<String,Movie> movies = filterUtil.getMoviesByFilters(MovieDB.getInstance().getMovies());

        System.out.println("Count of movies: " + movies.size());
        movies.values().forEach(System.out::println);
    }





}
