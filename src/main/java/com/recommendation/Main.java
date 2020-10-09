package com.recommendation;


import com.recommendation.model.Rater;
import com.recommendation.model.Rating;
import com.recommendation.util.ClosenessUtil;
import com.recommendation.util.RaterUtil;

import java.util.*;

public class Main {


    public static void main(String[] args){

        String me = "735";
        HashMap<String,Double> closenesses = ClosenessUtil.getInstance().getTopNCloseness(me,20);



        for(String closenessId: closenesses.keySet()){
            System.out.println(me + "-" + closenessId + ": " + closenesses.get(closenessId));
            RaterUtil.getInstance().printCommonRatedMovie(MovieDB.getInstance().getRaters().get(me),MovieDB.getInstance().getRaters().get(closenessId));
            printRecommendations(MovieDB.getInstance().getRaters().get(me),MovieDB.getInstance().getRaters().get(closenessId),100);
        }

        //bütün yakınlardan gelen filmler bir collection da toplanır ve filmler ortalama skorlaruna göre önerilir.
        //getRecomments
        //getWeightedAvarages
        //printAvarages



    }

    public static void printRecommendations(Rater r1, Rater r2, int n){
        ArrayList<Rating> notCommonMovies = new ArrayList<>();
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
                notCommonMovies.add(r2Rating);
                count++;
            }

            if(count == n){
                break;
            }
        }
        notCommonMovies.forEach((Rating rating) -> System.out.println(rating.getValue() + ":   " + MovieDB.getInstance().getMovies().get(rating.getItem())));

    }

}
