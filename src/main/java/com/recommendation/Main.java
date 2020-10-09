package com.recommendation;


import com.recommendation.model.Movie;
import com.recommendation.model.Rater;
import com.recommendation.model.Rating;
import com.recommendation.model.filter.DirectorFilter;
import com.recommendation.model.filter.GenreFilter;
import com.recommendation.model.filter.MinutesFilter;
import com.recommendation.model.filter.YearsAfterFilter;
import com.recommendation.util.FilterUtil;

import java.util.*;

public class Main {

    static FilterUtil filterUtil;

    public static void main(String[] args){

        String me = "735";
        HashMap<String,Double> closenesses = getWeightedAvarages(me);
        closenesses.remove("735");


        for(Map.Entry<String,Double> closeness:findTopNCloseness(30,closenesses).entrySet()){
            System.out.println(me + "-" + closeness.getKey() + ": " + closeness.getValue());
            printCommonRatedMovie(MovieDB.getInstance().getRaters().get(me),MovieDB.getInstance().getRaters().get(closeness.getKey()));
            printRecommendations(MovieDB.getInstance().getRaters().get(me),MovieDB.getInstance().getRaters().get(closeness.getKey()),10);
        }


        closenesses.entrySet().forEach((Map.Entry<String,Double> entry) ->{
            System.out.println(entry.getKey() + ": " + entry.getValue());
        });

    }

    public static HashMap<String,Double> findTopNCloseness(int n,HashMap<String,Double> closenesses){

        HashMap<String, Double> topNCloseness = new LinkedHashMap<>();

        for(int i = 0; i < n; i++){
            String maxId = "";

            for(Map.Entry<String,Double> closeness: closenesses.entrySet()){
                if(!topNCloseness.containsKey(closeness.getKey())){
                    if(closeness.getValue() != 0){
                        if(maxId.isEmpty()){
                            maxId = closeness.getKey();

                        }else if(closenesses.get(maxId) < closeness.getValue()){
                            maxId = closeness.getKey();
                        }
                    }


                }


            }
            topNCloseness.put(maxId,closenesses.get(maxId));
        }

        return topNCloseness;
    }

    public static void printCommonRatedMovie(Rater r1, Rater r2){

        for(Rating r1Rating :r1.getMyRatings()){
            for (Rating r2Rating: r2.getMyRatings()){
                if(r1Rating.getItem().equals(r2Rating.getItem())){
                    System.out.println(r1Rating + "  :  " + r2Rating);
                    break;
                }
            }
        }

    }

    public static void printRecommendations(Rater r1, Rater r2, int n){
        ArrayList<Rating> notCommonMovies = new ArrayList<>();
        int count = 0;
        r2.getMyRatings().sort(Rating::compareTo);
        for(Rating r2Rating: r2.getMyRatings()){
            boolean isUnique = true;
            for (Rating r1Rating :r1.getMyRatings()){
                if(r1Rating.getItem().equals(r2Rating.getItem())){
                    isUnique = false;
                    break;
                }
            }
            if(isUnique){
                notCommonMovies.add(r2Rating);
                count++;
            }

            if(count == n){
                break;
            }
        }
        notCommonMovies.forEach((Rating rating) -> System.out.println(rating.getValue() + ":   " + MovieDB.getInstance().getMovies().get(rating.getItem())));

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
        int count = 0;
        for(Rating r1Rating:r1.getMyRatings()){
            for(Rating r2Rating: r2.getMyRatings()){
                if(r1Rating.getItem().equals(r2Rating.getItem())){
                    closeness += Math.abs(r1Rating.getValue()-r2Rating.getValue());
                    count++;
                    break;
                }
            }
        }
        if(count != 0){
            closeness /= count;
        }

        return -closeness;
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
