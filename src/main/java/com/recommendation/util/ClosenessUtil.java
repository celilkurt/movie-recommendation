package com.recommendation.util;

import com.recommendation.MovieDB;
import com.recommendation.model.Rater;
import com.recommendation.model.Rating;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ClosenessUtil {

    private static ClosenessUtil instance;

    private ClosenessUtil(){    }

    public static ClosenessUtil getInstance() {
        if(instance == null){
            instance = new ClosenessUtil();
        }
        return instance;
    }

    public HashMap<String,Double> getTopNCloseness(String id, int n){

        HashMap<String,Double> closenesses = getClosenesses(id);
        HashMap<String, Double> topNCloseness = new LinkedHashMap<>();

        for(int i = 0; i < n; i++){
            String minId = "";

            for(Map.Entry<String,Double> closeness: closenesses.entrySet()){
                if(!topNCloseness.containsKey(closeness.getKey())){
                    if(closeness.getValue() != 0){
                        if(minId.isEmpty()){
                            minId = closeness.getKey();

                        }else if(closenesses.get(minId) > closeness.getValue()){
                            minId = closeness.getKey();
                        }
                    }
                }
            }
            if(!minId.isEmpty()){
                topNCloseness.put(minId,closenesses.get(minId));
            }
        }

        return topNCloseness;
    }



    public HashMap<String, Double> getClosenesses(String id){

        HashMap<String, Rater> raters = MovieDB.getInstance().getRaters();
        Rater rater;

        if(raters.containsKey(id)){
            rater = raters.get(id);
        }else{
            return null;
        }

        HashMap<String,Double> closenessMap = new HashMap<>();

        for(Rater tempRater: raters.values()){
            if(!tempRater.getID().equals(id)){
                Double closeness = findCloseness(rater,tempRater);
                if(closeness != 0){
                    closenessMap.put(tempRater.getID(),findCloseness(rater,tempRater));
                }

            }
        }

        return closenessMap;
    }

    public double findCloseness(Rater r1, Rater r2){

        double closeness = 0;
        int count = 0;
        int sameRateCount = 0;
        for(Rating r1Rating:r1.getMyRatings()){
            for(Rating r2Rating: r2.getMyRatings()){
                if(r1Rating.getItem().equals(r2Rating.getItem())){
                    closeness += Math.abs(r1Rating.getValue()-r2Rating.getValue());
                    count++;
                    if((r1Rating.getValue() <= 5 && r2Rating.getValue() <= 5) || (r1Rating.getValue() >= 5 && r2Rating.getValue() >= 5)){
                        sameRateCount ++;
                    }
                    break;
                }
            }
        }
        if(count != 0){
            closeness /= count;
            closeness -= sameRateCount;
        }

        return closeness;
    }


}
