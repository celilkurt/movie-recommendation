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

    public void fetchRatingsByFileName(ArrayList<Rater> raters, ArrayList<Rating> ratings, String fileName){

        try{
            int count = 0;
            CSVReader csvReader = new CSVReader(new FileReader("ratings.csv"));
            String[] values = null;
            values = csvReader.readNext();
            while ((values = csvReader.readNext()) != null) {
                String id = values[0].trim();
                Rater rater = findRaterWithID(raters, id);

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
