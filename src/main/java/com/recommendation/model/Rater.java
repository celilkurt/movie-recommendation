package com.recommendation.model;

import java.util.ArrayList;

public class Rater {

    private String myID;
    private ArrayList<Rating> myRatings;

    public Rater(String id){
        myID = id;
        myRatings = new ArrayList<Rating>();
    }

    public void addRating(Rating rating){
        myRatings.add(rating);
    }

    public boolean hasRating(String item){

        for(Rating rating: myRatings){
            if(rating.getItem().equals(item)){
                return true;
            }
        }
        return false;
    }

    public String getID(){ return myID; }

    public double getRating(String item){
        for(Rating rating: myRatings){
            if(rating.getItem().equals(item)){
                return rating.getValue();
            }
        }
        return -1;
    }

    public ArrayList<Rating> getMyRatings() {
        return myRatings;
    }

    public int numRatings(){ return myRatings.size(); }

    public ArrayList<String> getItemsRated(){
        ArrayList<String> items = new ArrayList<String>();
        for(Rating rating: myRatings){
            items.add(rating.getItem());
        }
        return items;
    }
}
