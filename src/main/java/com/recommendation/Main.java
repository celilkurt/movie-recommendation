package com.recommendation;


import com.recommendation.filter.DirectorFilter;
import com.recommendation.filter.MinutesFilter;
import com.recommendation.filter.YearsAfterFilter;
import com.recommendation.model.Movie;
import com.recommendation.model.Rating;
import com.recommendation.util.FilterUtil;
import com.recommendation.util.RecommendationUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {


    public static void main(String[] args){

        String meID = "65";
        FilterUtil filterUtil = new FilterUtil(20, new MinutesFilter(new int[]{100,150}),new YearsAfterFilter(2014),new DirectorFilter("Wes Anderson, Clint Eastwood"));
        ArrayList<Rating> movies = RecommendationUtil.getInstance().getSimilarRatingsWithFilters(meID, 10, 3,filterUtil);




        for (Rating rating: movies){
            System.out.println(rating.getItem() + " - " + rating.getValue() +
                    "\n" + Database.getInstance().getMovies().get(rating.getItem()));
        }

    }

}
