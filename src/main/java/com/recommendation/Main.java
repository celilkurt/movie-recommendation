package com.recommendation;


import com.recommendation.model.Movie;
import com.recommendation.model.Rater;
import com.recommendation.model.Rating;
import com.recommendation.util.ClosenessUtil;
import com.recommendation.util.RaterUtil;

import java.util.*;

public class Main {


    public static void main(String[] args){

        String me = "735";
        int minCommonRating = MovieDB.getInstance().getRaters().get(me).getMyRatings().size();
        if(minCommonRating < 10 ){
            ClosenessUtil.getInstance().setMinRating(1);
        }

        HashMap<String,Double> closenesses = ClosenessUtil.getInstance().getTopNCloseness(me,20);

        HashMap<String,Double> recoms = getMoviesWithWeightedAvarages(me,closenesses);

        ArrayList<Rating> recomsWithWeightedAvarages = new ArrayList<>();
        for(Map.Entry<String, Double> recom: recoms.entrySet()){
            recomsWithWeightedAvarages.add(new Rating(recom.getKey(),recom.getValue()));
        }

        recomsWithWeightedAvarages.sort(Rating::compareTo);
        recomsWithWeightedAvarages.forEach((Rating rating) -> {
            System.out.println(rating.getValue() + " : " + MovieDB.getInstance().getMovies().get(rating.getItem()));
        });
        
    }

    public static HashMap<String,Double> getMoviesWithWeightedAvarages(String me,HashMap<String,Double> closenesses){

        HashMap<String,Double> recomsWithWeights = new HashMap<>();
        for(String closenessId: closenesses.keySet()){
            ArrayList<Rating> ratings = getTopNRecommendations(MovieDB.getInstance().getRaters().get(me),MovieDB.getInstance().getRaters().get(closenessId),10);

            for(Rating rating: ratings){
                if(recomsWithWeights.containsKey(rating.getItem())){
                    recomsWithWeights.put(rating.getItem(), recomsWithWeights.get(rating.getItem()) + rating.getValue()*Math.pow(closenesses.get(closenessId),-1));
                }else{
                    recomsWithWeights.put(rating.getItem(), rating.getValue()*Math.pow(closenesses.get(closenessId),-1));
                }
            }
        }
        return recomsWithWeights;
    }

    public static ArrayList<Rating> getTopNRecommendations(Rater r1, Rater r2, int n){

        ArrayList<Rating> ratings = new ArrayList<>();
        int count = 0;
        r2.getMyRatings().sort(Rating::compareTo);
        for(Rating r2Rating: r2.getMyRatings()){
            boolean isUnique = true;
            for (Rating r1Rating :r1.getMyRatings()){
                if(r1Rating.getItem().equals(r2Rating.getItem())){
                    isUnique = false;
                    break;
                }
            }
            if(isUnique){
                ratings.add(r2Rating);
                count++;
            }

            if(count == n){
                break;
            }
        }

        return ratings;
    }

}
