package com.recommendation;

import com.opencsv.CSVReader;
import com.recommendation.model.Movie;
import com.recommendation.model.Rater;
import com.recommendation.model.Rating;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MovieDB {

    private static MovieDB instance;
    private static HashMap<String,Movie> movies;
    private static HashMap<String,Rater> raters;
    private static HashMap<String,Rating> ratings;
    private static HashMap<String,Integer> ratingFrequence ;
    private static final Logger logger = Logger.getLogger(MovieDB.class);

    private MovieDB(){
        movies = new HashMap<>();
        raters = new HashMap<>();
        ratings = new HashMap<>();
        ratingFrequence = new HashMap<>();
    }


    public static MovieDB getInstance(){
        if(instance == null){
            instance = new MovieDB();
            instance.fetchRatingsByFileName("ratings.csv");
            instance.setRatingFrequence();
            instance.fetchMoviesByFileName("ratedmoviesfull.csv");

        }
        return instance;
    }

    private void setRatingFrequence(){
        for(Map.Entry<String,Rating> rating: ratings.entrySet()){
            if(ratingFrequence.containsKey(rating.getKey())){
                ratingFrequence.put(rating.getKey(),ratingFrequence.get(rating.getKey()) +1);
            }else{
                ratingFrequence.put(rating.getKey(),1);
            }
        }
    }

    public HashMap<String, Movie> getMovies() {
        return movies;
    }

    public HashMap<String, Rater> getRaters() {
        return raters;
    }

    public HashMap<String, Rating> getRatings() {
        return ratings;
    }

    public HashMap<String, Integer> getRatingFrequence() {
        return ratingFrequence;
    }

    public void fetchMoviesByFileName(String fileName){
        try{
            CSVReader csvReader = new CSVReader(new FileReader(fileName));
            String[] values = csvReader.readNext();
            while ((values = csvReader.readNext()) != null) {
                movies.put(values[0],new Movie(values[0],values[1],values[2],values[4],values[5],values[3],values[7],values[6]));
            }

            logger.info("Number of films: " + movies.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void fetchRatingsByFileName(String fileName){

        try{
            CSVReader csvReader = new CSVReader(new FileReader(fileName));
            String[] values = csvReader.readNext();
            while ((values = csvReader.readNext()) != null) {
                String id = values[0];

                Rater rater ;
                if(raters.containsKey(id)){
                    rater = raters.get(id);
                }else{
                    rater = new Rater(id);
                    raters.put(id,rater);
                }

                Rating rating = new Rating(values[1],Double.parseDouble(values[2]));
                ratings.put(values[1],rating);
                rater.addRating(rating);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public Movie getMovieById(String id){

        for(Movie movie: movies.values()){
            if(movie.getId().equals(id)){
                return movie;
            }
        }
        return null;
    }


}
