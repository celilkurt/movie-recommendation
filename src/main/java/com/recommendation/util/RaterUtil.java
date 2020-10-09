package com.recommendation.util;

import com.opencsv.CSVReader;
import com.recommendation.model.Rater;
import com.recommendation.model.Rating;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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

    public int findNumberOfRatingWithRaterID(HashMap<String, Rater> raters, String id){
        return raters.get(id).numRatings();
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


}
