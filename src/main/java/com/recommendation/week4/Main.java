package com.recommendation.week4;

import com.recommendation.MovieDB;
import com.recommendation.model.Movie;
import com.recommendation.model.Rater;
import com.recommendation.model.Rating;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static HashMap<String,Integer> rateFrequence = new HashMap<>();
    static int minRater = 10;

    public static void main(String[] args){
        String meID = "800";
        ArrayList<Rating> weightedAvarages = getAllWeightedAvaragesByRaterId(meID);
        weightedAvarages.sort(Rating::compareTo);

        weightedAvarages.forEach((Rating rating) -> {
            System.out.println(MovieDB.getInstance().getMovies().get(rating.getItem()));
            System.out.println(rating.getValue());
            System.out.println("Rate frequence: " + rateFrequence.get(rating.getItem()));
        });


    }

    public static ArrayList<Rating> getAllWeightedAvaragesByRaterId(String meId){

        ArrayList<Rating> weightedAvarages = new ArrayList<>();
        Rater me = MovieDB.getInstance().getRaters().get(meId);
        int count = 0;
        for(Rater rater: MovieDB.getInstance().getRaters().values()){
            if(!rater.getID().equals(me.getID()) && minRater <= getCommonMoviesCount(me,rater)){
                ArrayList<Rating> ratWeightedAvarages = getNotCommonMoviesAvarages(me,rater,getWeightByRater(me,rater));
                for(Rating rating: ratWeightedAvarages){
                    Rating curRating = getRatingById(rating.getItem(),weightedAvarages);
                    if(curRating != null){
                        rateFrequence.put(rating.getItem(),rateFrequence.get(rating.getItem()) + 1);
                        Double newAvarage = (curRating.getValue() + rating.getValue())/2;
                        curRating.setValue(newAvarage);
                    }else{
                        weightedAvarages.add(rating);
                        rateFrequence.put(rating.getItem(),1);
                    }
                }
                count++;
            }
        }
        System.out.println("count: " + count);
        return weightedAvarages;
    }

    public static Rating getRatingById(String id, ArrayList<Rating> ratings) {

        for(Rating rating: ratings) {
            if(rating.getItem().equals(id)) {
                return rating;
            }
        }
        return null;
    }


    public static ArrayList<Rating> getNotCommonMoviesAvarages(Rater me, Rater rater, int weightOfRater){
        ArrayList<Rating> ratings = new ArrayList<>();
        for(Rating myRating: me.getMyRatings()){
            for(Rating ratRating: rater.getMyRatings()){
                if(!myRating.getItem().equals(ratRating.getItem())){
                    ratings.add(new Rating(ratRating.getItem(),weightOfRater*ratRating.getValue()));
                }
            }
        }
        return ratings;
    }

    public static int getCommonMoviesCount(Rater me, Rater rater){
        int count = 0;
        for(Rating myRating: me.getMyRatings()){
            for(Rating ratRating: rater.getMyRatings()){
                if(myRating.getItem().equals(ratRating.getItem())){
                    count++;
                }
            }
        }
        return count;
    }

    public static int getWeightByRater(Rater me, Rater rater){

        int weight = 0;
        for(Rating myRating: me.getMyRatings()){
            for(Rating r2Rating: rater.getMyRatings()){
                if(myRating.getItem().equals(r2Rating.getItem())){
                    weight += ((int)myRating.getValue()-5)*((int)r2Rating.getValue()-5);
                }
            }
        }
        return weight;
    }



}
