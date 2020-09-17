package com.recommendation.util;


import com.opencsv.CSVReader;
import com.recommendation.model.Movie;
import com.recommendation.model.Rater;
import com.recommendation.model.Rating;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.apache.log4j.Logger;


public class MovieUtil {

    private static MovieUtil instance;
    private static final Logger logger = Logger.getLogger(MovieUtil.class);


    private MovieUtil(){

    }

    public static MovieUtil getInstance(){

        if(instance == null){
            instance = new MovieUtil();
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

    public int findNumberOfFilmByGenre(ArrayList<Movie> movies,String genre){

        int count = 0;
        for(Movie movie: movies){
            if(movie.getGenres().toLowerCase().contains(genre.toLowerCase())){
                count++;
            }
        }
        return count;
    }

    public int findNumberOfFilmLargerThanByMinutes(ArrayList<Movie> movies, int minute){
        int count = 0;
        for(Movie movie:movies){

            if(movie.getMinutes() > minute){
                count++;
            }
        }

        return count;
    }

    public int findMaxNumOfFilmDirectedByAnyDirector(ArrayList<Movie> movies){

        HashMap<String,Integer> numOfDirectorFilms = new HashMap<String, Integer>();
        for(Movie movie:movies){

            String[] directors = movie.getDirector().split(",");
            for(String director: directors){
                if(numOfDirectorFilms.containsKey(director)){
                    numOfDirectorFilms.put(director,numOfDirectorFilms.get(director) + 1);
                }else{
                    numOfDirectorFilms.put(director,1);
                }
            }
        }

        String director = "";
        int max = 0;

        for(Map.Entry<String,Integer> entry: numOfDirectorFilms.entrySet()){
            if(entry.getValue() > max){
                max = entry.getValue();
                director = entry.getKey();
            }
        }

        return max;
    }


    public String findDirectorMostFilmDirected(ArrayList<Movie> movies){
        //What is the maximum number of films directed by one director?
        HashMap<String,Integer> numOfDirectorFilms = new HashMap<String, Integer>();
        for(Movie movie:movies){

            String[] directors = movie.getDirector().split(",");
            for(String director: directors){
                if(numOfDirectorFilms.containsKey(director)){
                    numOfDirectorFilms.put(director,numOfDirectorFilms.get(director) + 1);
                }else{
                    numOfDirectorFilms.put(director,1);
                }
            }
        }

        String director = "";
        int max = 0;

        for(Map.Entry<String,Integer> entry: numOfDirectorFilms.entrySet()){
            if(entry.getValue() > max){
                max = entry.getValue();
                director = entry.getKey();
            }
        }

        return director;
    }

    public int findNumOfRatedUniqueMovie(ArrayList<Rater> raters){
        //What is the total number of unique movies that have been rated?

        HashMap<String,Integer> numOfFilmRates = new HashMap<String, Integer>();

        for(Rater rater: raters){
            for(Rating rating: rater.getMyRatings()){
                if(numOfFilmRates.containsKey(rating.getItem())){
                    numOfFilmRates.put(rating.getItem(),numOfFilmRates.get(rating.getItem()) + 1);
                }else{
                    numOfFilmRates.put(rating.getItem(),1);
                }
            }
        }


        int count = 0;

        for(Map.Entry<String,Integer> entry: numOfFilmRates.entrySet()){
            if(entry.getValue() == 1){
                count++;
            }
        }

        return count;
    }

    public Movie findMovieWithID(ArrayList<Movie> movies, String id){
        for(Movie movie: movies){
            if(movie.getId().equals(id)){
                return movie;
            }
        }
        return null;
    }

    public double getAverageByID(ArrayList<Rating> ratings,String ID, int minimalRaters){

        int sum = 0;
        int count = 0;

        for(Rating rating: ratings){
            if(rating.getItem().equals(ID)){
                sum += rating.getValue();
                count++;
            }
        }

        if(count >= minimalRaters){
            return (double)sum/(double)count;
        }else{
            return Double.parseDouble("0");
        }

    }

    public ArrayList<Rating> getAverageRatings(ArrayList<Rating> ratings, int minimalRaters){

        ArrayList<Rating> avarageRatings = new ArrayList<Rating>();

        HashSet<String> IDs = new HashSet<String>();
        for(Rating rating: ratings){
            IDs.add(rating.getItem());
        }

        for(String ID :IDs){
            Double avarageRating = getAverageByID(ratings,ID,minimalRaters);
            if(avarageRating != 0.0){
                avarageRatings.add(new Rating(ID,avarageRating));
            }
        }

        return avarageRatings;
    }


}
