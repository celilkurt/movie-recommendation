package com.recommendation.util;

import com.recommendation.Database;
import com.recommendation.filter.Filter;
import com.recommendation.model.Rater;
import com.recommendation.model.Rating;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecommendationUtil {

    private static RecommendationUtil instance;

    private RecommendationUtil(){}

    public static RecommendationUtil getInstance(){
        if(instance == null){
            instance = new RecommendationUtil();
        }
        return instance;
    }

    public ArrayList<Rating> getSimilarRatingsWithFilters(String id, int numSimilarRaters, int minmalRaters, FilterUtil filterUtil){

        ArrayList<Rating> moviesPreFiltering = getRecomMovies(id,numSimilarRaters,minmalRaters);
        ArrayList<Rating> moviesAfterFiltering = new ArrayList<>();

        for(Rating rating: moviesPreFiltering){
            if(filterUtil.isMatchById(rating.getItem())) {
                moviesAfterFiltering.add(rating);
            }
        }

        return moviesAfterFiltering;

    }

    public ArrayList<Rating> getRecomMovies(String id, int numSimilarRaters, int minmalRaters){
        ArrayList<Rating> ratersWithSimilarities = getSimilarities(Database.getInstance().getRaters().get(id),numSimilarRaters);
        HashMap<String,Rating> averages = new HashMap<>();
        HashMap<String,Integer> frequencies = new HashMap<>();
        Rater me = Database.getInstance().getRaters().get(id);


        for(Rating similarityById: ratersWithSimilarities){
            Rater rater = Database.getInstance().getRaters().get(similarityById.getItem());
            Double similarity = similarityById.getValue();

            for(Rating rating: rater.getMyRatings()){
                if(getRatingById(rating.getItem(),me.getMyRatings()) == null){
                    if(averages.containsKey(rating.getItem())){
                        Double newAverage = averages.get(rating.getItem()).getValue() + rating.getValue()*similarity;
                        averages.get(rating.getItem()).setValue(newAverage);
                        frequencies.put(rating.getItem(), frequencies.get(rating.getItem()) + 1);

                    }else{
                        averages.put(rating.getItem(),rating);
                        frequencies.put(rating.getItem(), 1);
                    }
                }
            }
        }
        //Yeterli değerlendirmeye sahipse ortalama hesaplanır ve dinamik bir diziye atanır.
        ArrayList<Rating> weightedAverages = new ArrayList<>();
        for(Map.Entry<String,Integer> frequency: frequencies.entrySet()){
            if(frequency.getValue() >= minmalRaters){
                Double newAvarage = averages.get(frequency.getKey()).getValue()/frequency.getValue();
                averages.get(frequency.getKey()).setValue(newAvarage);
                weightedAverages.add(averages.get(frequency.getKey()));
            }
        }
        weightedAverages.sort(Rating::compareTo);
        return weightedAverages;
    }

    public Rating getRatingById(String id, ArrayList<Rating> ratings){
        for(Rating rating: ratings){
            if(rating.getItem().equals(id)){
                return rating;
            }
        }
        return null;
    }

    public ArrayList<Rating> getSimilarities(Rater me, int numSimilarRaters){
        ArrayList<Rating> similarities = new ArrayList<>();
        for(Rater rater: Database.getInstance().getRaters().values()){
            if(!rater.getID().equals(me.getID())){
                int similarity = getSimilarity(me,rater);
                if(similarity > 0){
                    similarities.add(new Rating(rater.getID(),similarity));
                }
            }
        }
        similarities.sort(Rating::compareTo);
        ArrayList<Rating> topNRaterBySimilarities = new ArrayList<>();
        for(int i = 0; i < numSimilarRaters; i++){
            topNRaterBySimilarities.add(similarities.get(i));
        }

        return topNRaterBySimilarities;
    }


    public int getSimilarity(Rater me, Rater rater){

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

