package com.recommendation;

import com.opencsv.CSVReader;
import com.recommendation.model.Avarage;
import com.recommendation.model.Movie;
import com.recommendation.model.Rater;
import com.recommendation.model.Rating;
import com.recommendation.model.filter.Filter;
import com.recommendation.util.MovieUtil;
import com.recommendation.util.RaterUtil;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MovieDB {

    private static MovieDB instance;
    private static ArrayList<Movie> movies;
    private static ArrayList<Rater> raters;
    private static ArrayList<Rating> ratings;
    private static HashMap<String,Integer> ratingFrequence ;
    private static final Logger logger = Logger.getLogger(MovieDB.class);

    private MovieDB(){
        movies = new ArrayList<>();
        raters = new ArrayList<>();
        ratings = new ArrayList<>();
        ratingFrequence = new HashMap<>();
    }

    public ArrayList<Movie> getMovies(){ return movies; }

    public static MovieDB getInstance(){
        if(instance == null){
            instance = new MovieDB();
            instance.fetchRatingsByFileName(raters,ratings,"ratings.csv");
            instance.setRatingFrequence();
            instance.fetchMoviesByFileName(movies,"ratedmoviesfull.csv");

        }
        return instance;
    }

    private void setRatingFrequence(){
        for(Rating rating: ratings){
            if(ratingFrequence.containsKey(rating.getItem())){
                ratingFrequence.put(rating.getItem(),ratingFrequence.get(rating.getItem()) +1);
            }else{
                ratingFrequence.put(rating.getItem(),1);
            }
        }
    }

    public static ArrayList<Rater> getRaters() {
        return raters;
    }

    public static ArrayList<Rating> getRatings() {
        return ratings;
    }

    public static HashMap<String, Integer> getRatingFrequence() {
        return ratingFrequence;
    }

    public void fetchMoviesByFileName(ArrayList<Movie> movies, String fileName){
        try{
            CSVReader csvReader = new CSVReader(new FileReader(fileName));
            String[] values = csvReader.readNext();
            while ((values = csvReader.readNext()) != null) {
                movies.add(new Movie(values[0],values[1],values[2],values[4],values[5],values[3],values[7],values[6]));

            }

            logger.info("Number of films: " + movies.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void fetchRatingsByFileName(ArrayList<Rater> raters, ArrayList<Rating> ratings, String fileName){

        try{
            CSVReader csvReader = new CSVReader(new FileReader(fileName));
            String[] values = csvReader.readNext();
            while ((values = csvReader.readNext()) != null) {
                String id = values[0];
                Rater rater = RaterUtil.getInstance().findRaterWithID(raters, id);

                if(rater == null){
                    rater = new Rater(id);
                    raters.add(rater);
                }
                Double rate = Double.parseDouble(values[2]);
                Rating rating = new Rating(values[1],rate);
                ratings.add(rating);
                rater.addRating(rating);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public Movie getMovieById(String id){

        for(Movie movie: movies){
            if(movie.getId().equals(id)){
                return movie;
            }
        }
        return null;
    }


}
