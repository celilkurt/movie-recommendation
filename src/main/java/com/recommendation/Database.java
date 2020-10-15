package com.recommendation;

import com.opencsv.CSVReader;
import com.recommendation.model.Movie;
import com.recommendation.model.Rater;
import com.recommendation.model.Rating;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {

    private static Database instance;
    private static HashMap<String,Movie> movies;
    private static HashMap<String,Rater> raters;
    private static HashMap<String, ArrayList<Double>> ratings;
    private static final Logger logger = Logger.getLogger(Database.class);

    private Database(){
        movies = new HashMap<>();
        raters = new HashMap<>();
        ratings = new HashMap<>();
    }


    public static Database getInstance(){
        if(instance == null){
            instance = new Database();
            instance.fetchRatingsByFileName("ratings.csv");
            instance.fetchMoviesByFileName("ratedmoviesfull.csv");

        }
        return instance;
    }



    public HashMap<String, Movie> getMovies() {
        return movies;
    }

    public HashMap<String, Rater> getRaters() {
        return raters;
    }

    public HashMap<String, ArrayList<Double>> getRatings() {
        return ratings;
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
                String userId = values[0];
                String movieId = values[1];
                Double rate = Double.parseDouble(values[2]);

                Rater rater ;
                if(raters.containsKey(userId)){
                    rater = raters.get(userId);
                }else{
                    rater = new Rater(userId);
                    raters.put(userId,rater);
                }

                Rating rating = new Rating(movieId,rate);
                rater.addRating(rating);

                if(ratings.containsKey(movieId)){
                    ratings.get(movieId).add(rate);
                }else{
                    ArrayList<Double> rateList = new ArrayList<>();
                    rateList.add(rate);
                    ratings.put(movieId,rateList);
                }

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
