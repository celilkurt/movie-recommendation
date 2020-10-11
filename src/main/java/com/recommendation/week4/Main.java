package com.recommendation.week4;

import com.recommendation.MovieDB;
import com.recommendation.model.Movie;
import com.recommendation.model.Rater;
import com.recommendation.model.Rating;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args){
        String meID = "735";
        Rater me = MovieDB.getInstance().getRaters().get(meID);
        HashMap<String, ArrayList<int[]>> similarities = new HashMap<>();
        for(String id: MovieDB.getInstance().getRaters().keySet()){
            HashMap<String,int[]> tempSimilarities = getSimilarities(me,MovieDB.getInstance().getRaters().get(id));
            for(Map.Entry<String,int[]> entry: tempSimilarities.entrySet()){
                if(similarities.containsKey(entry.getKey())){
                    similarities.get(entry.getKey()).add(entry.getValue());
                }else{
                    ArrayList<int[]> ratings = new ArrayList<>();
                    ratings.add(entry.getValue());
                    similarities.put(entry.getKey(), ratings);
                }
            }

        }
        int max = 0;
        String maxId = "";
        for(Map.Entry<String, ArrayList<int[]>> entry: similarities.entrySet()){
            System.out.println("id: " + entry.getKey());
            if(max < entry.getValue().size()){
                max = entry.getValue().size();
                maxId = entry.getKey();
            }
            for(int[] ratings:entry.getValue()){
                System.out.println(ratings[0] + " - " + ratings[1]);
            }
        }
        System.out.println("'" + maxId + "' has " + max + " ratings");

    }

    public static HashMap<String,int[]> getSimilarities(Rater me, Rater r2){

        HashMap<String,int[]> similarities = new HashMap<>();
        for(Rating myRating: me.getMyRatings()){
            for(Rating r2Rating: r2.getMyRatings()){
                if(myRating.getItem().equals(r2Rating.getItem())){
                    similarities.put(myRating.getItem(),new int[]{((int)myRating.getValue())-5,(int)r2Rating.getValue()-5});
                }
            }
        }

        return similarities;
    }


}
