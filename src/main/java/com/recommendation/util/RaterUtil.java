package com.recommendation.util;

import com.opencsv.CSVReader;
import com.recommendation.model.Rater;
import com.recommendation.model.Rating;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RaterUtil {

    private static RaterUtil instance;
    private static final Logger logger = Logger.getLogger(RaterUtil.class);

    private RaterUtil(){

    }

    public static RaterUtil getInstance(){
        if(instance == null){
            instance = new RaterUtil();
        }
        return instance;
    }



    public int findMaxRatingsNumber(ArrayList<Rater> raters){

        int maxRatings = 0;
        for(Rater rater: raters){
            if(rater.numRatings() > maxRatings){
                maxRatings = rater.numRatings();
            }
        }
        return maxRatings;
    }

    public int findNumberOfRatingWithRaterID(ArrayList<Rater> raters, String id){
        int count = findRaterWithID(raters,id).numRatings();
        return count;
    }



    public int findNumberOfRatingWithMovieID(ArrayList<Rater> raters, String id){

        int count = 0;
        for(Rater rater: raters){

            if(rater.getRating(id) != -1){
                count++;
            }
        }

        return count;

    }

    public Rater findRaterWithID(ArrayList<Rater> raters, String id){
        for(Rater rater: raters){
            if(rater.getID().equals(id)){
                return rater;
            }
        }
        return null;
    }
}
