package com.recommendation;

import com.opencsv.CSVReader;
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
import java.util.List;

public class MovieDB {

    private static MovieDB instance;
    private static ArrayList<Movie> movies;
    private static ArrayList<Rater> raters;
    private static ArrayList<Rating> ratings;
    private static final Logger logger = Logger.getLogger(MovieDB.class);

    private MovieDB(){
        movies = new ArrayList<>();
        raters = new ArrayList<>();
        ratings = new ArrayList<>();
    }

    public static MovieDB getInstance(){
        if(instance == null){
            instance = new MovieDB();
            instance.fetchMoviesByFileName(movies,"ratedmoviesfull.csv");
            instance.fetchRatingsByFileName(raters,ratings,"ratings.csv");
        }
        return instance;
    }

    public void fetchMoviesByFileName(ArrayList<Movie> movies, String fileName){
        try{
            CSVReader csvReader = new CSVReader(new FileReader(fileName));
            String[] values = null;
            values = csvReader.readNext();
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
            int count = 0;
            CSVReader csvReader = new CSVReader(new FileReader("ratings.csv"));
            String[] values = null;
            values = csvReader.readNext();
            while ((values = csvReader.readNext()) != null) {
                String id = values[0].trim();
                Rater rater = RaterUtil.getInstance().findRaterWithID(raters, id);

                if(rater == null){
                    rater = new Rater(values[0].trim());
                    raters.add(rater);
                }
                ratings.add(new Rating(values[1],Double.parseDouble(values[2])));
                rater.addRating(values[1],Double.parseDouble(values[2]));
                count++;
            }

            logger.info("Number of Ratings: " + count);
            logger.info("Number of Raters: " + raters.size());


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Movie> getMoviesByFilter(Filter filter){
        return filter.getMovies( movies);
    }






}
