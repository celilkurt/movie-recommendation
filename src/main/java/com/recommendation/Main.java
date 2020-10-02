package com.recommendation;

import com.recommendation.model.Movie;
import com.recommendation.model.Rater;
import com.recommendation.model.Rating;
import com.recommendation.util.MovieUtil;
import com.recommendation.util.RaterUtil;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    private static ArrayList<Movie> movies;
    private static ArrayList<Rater> raters;
    private static ArrayList<Rating> ratings;

    public static void main(String[] args){

        movies = new ArrayList<Movie>();
        raters = new ArrayList<Rater>();
        ratings = new ArrayList<Rating>();

        MovieUtil.getInstance().fetchMoviesByFileName(movies,"ratedmoviesfull.csv");

        RaterUtil.getInstance().fetchRatingsByFileName(raters,ratings,"ratings.csv");

       

        ArrayList<Rating> avarageRatings = MovieUtil.getInstance().getAverageRatings(ratings,12);
        Collections.sort(avarageRatings);
        System.out.println("Num of avarages: " + avarageRatings.size());
        for(Rating avarageRating: avarageRatings){
            System.out.println(MovieUtil.getInstance().findMovieWithID(movies,avarageRating.getItem()));
            System.out.println("Avarage rating: " + avarageRating.getValue());
        }



    }





}
