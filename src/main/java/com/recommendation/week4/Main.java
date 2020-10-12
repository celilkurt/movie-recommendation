package com.recommendation.week4;

import com.recommendation.MovieDB;
import com.recommendation.model.Rater;
import com.recommendation.model.Rating;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static int minRater = 3;

    public static void main(String[] args){
        String meID = "735";
        getSimilarRatings(meID, 10, 3).forEach((Rating rating) -> {
            System.out.println(rating.getValue() + " - " + MovieDB.getInstance().getMovies().get(rating.getItem()));
        });

        /**ArrayList<Rating> weightedAvarages = getAllWeightedAvaragesByRaterId(meID);
        weightedAvarages.sort(Rating::compareTo);

        for (Rating rating : weightedAvarages) {
            System.out.println(MovieDB.getInstance().getMovies().get(rating.getItem()));
            System.out.println(rating.getValue());
        }*/



    }

    public static ArrayList<Rating> getAllWeightedAvaragesByRaterId(String meId){

        ArrayList<Rating> weightedAvarages = new ArrayList<>();
        Rater me = MovieDB.getInstance().getRaters().get(meId);
        int count = 0;
        for(Rater rater: MovieDB.getInstance().getRaters().values()){
            if(!rater.getID().equals(me.getID()) && isCommonMoviesCountEnough(me,rater)){
                int weightOfRater = getSimilarity(me,rater);
                for(Rating rating: getNotCommonMoviesRatings(me,rater,weightOfRater)){
                    Rating curRating = getRatingById(rating.getItem(),weightedAvarages);
                    if(curRating != null){
                        curRating.setValue((curRating.getValue()*2 + rating.getValue())/3);
                    }else{
                        weightedAvarages.add(rating);
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


    public static ArrayList<Rating> getNotCommonMoviesRatings(Rater me, Rater rater, int weightOfRater){
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

    public static boolean isCommonMoviesCountEnough(Rater me, Rater rater){
        int count = 0;
        for(Rating myRating: me.getMyRatings()){
            for(Rating ratRating: rater.getMyRatings()){
                if(myRating.getItem().equals(ratRating.getItem())){
                    count++;
                    break;
                }
            }
        }
        return count >= minRater;
    }


















    static ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minmalRaters){
        ArrayList<Rating> ratersWithSimilarities = getSimilarities(MovieDB.getInstance().getRaters().get(id));
        HashMap<String,Rating> avarages = new HashMap<>();
        HashMap<String,Integer> frequences = new HashMap<>();

        if(numSimilarRaters > ratersWithSimilarities.size()){
            numSimilarRaters = ratersWithSimilarities.size();
        }
        for(int i = 0; i < numSimilarRaters; i++){
            Rater rater = MovieDB.getInstance().getRaters().get(ratersWithSimilarities.get(i).getItem());
            Double similarity = ratersWithSimilarities.get(i).getValue();
            for(Rating rating: rater.getMyRatings()){
                if(avarages.containsKey(rating.getItem())){
                    Double newAvarage = avarages.get(rating.getItem()).getValue() + rating.getValue()*similarity;
                    avarages.get(rating.getItem()).setValue(newAvarage);
                    frequences.put(rating.getItem(), frequences.get(rating.getItem()) + 1);
                }else{
                    avarages.put(rating.getItem(),rating);
                    frequences.put(rating.getItem(), 1);
                }
            }
        }
        ArrayList<Rating> weightedAvarages = new ArrayList<>();
        for(Map.Entry<String,Integer> frequence: frequences.entrySet()){
            if(frequence.getValue() >= minmalRaters){
                Double newAvarage = avarages.get(frequence.getKey()).getValue()/frequence.getValue();
                avarages.get(frequence.getKey()).setValue(newAvarage);
                weightedAvarages.add(avarages.get(frequence.getKey()));
            }
        }
        weightedAvarages.sort(Rating::compareTo);
        return weightedAvarages;
    }

    static ArrayList<Rating> getSimilarities(Rater me){
        ArrayList<Rating> similarities = new ArrayList<>();
        for(Rater rater: MovieDB.getInstance().getRaters().values()){
            if(!rater.getID().equals(me.getID())){
                int similarity = getSimilarity(me,rater);
                if(similarity > 0){
                    similarities.add(new Rating(rater.getID(),similarity));
                }

            }
        }
        similarities.sort(Rating::compareTo);
        return similarities;
    }

    public static int getSimilarity(Rater me, Rater rater){

        int weight = 0;
        for(Rating myRating: me.getMyRatings()){
            for(Rating r2Rating: rater.getMyRatings()){
                if(myRating.getItem().equals(r2Rating.getItem())){
                    weight += ((int)myRating.getValue()-5)*((int)r2Rating.getValue()-5);
                    break;
                }
            }
        }
        return weight;
    }



}
