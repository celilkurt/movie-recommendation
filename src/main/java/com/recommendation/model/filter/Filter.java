package com.recommendation.model.filter;

import com.recommendation.model.Avarage;
import com.recommendation.model.Movie;
import com.recommendation.model.Rating;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Filter<Q> {

    Q query;
    public Filter(Q query){ this.query = query;}

    public abstract ArrayList<Movie> getMoviesByFilter(ArrayList<Movie> movies);

    public ArrayList<Avarage> getAvarages(ArrayList<Movie> movies, ArrayList<Rating> ratings) {

        ArrayList<Movie> resultMovies = getMoviesByFilter(movies);

        ArrayList<Avarage> avarages = new ArrayList<>();
        for(Movie movie: resultMovies){
            Avarage avarage = new Avarage(movie.getId());
            double sum = 0;
            int count = 0;

            for(Rating rating: ratings){
                if(rating.getItem().equals(movie.getId())){
                    count++;
                    sum += rating.getValue();
                }
            }
            avarage.setAvarage(sum/count);
            avarages.add(avarage);
        }
        Collections.sort(avarages);

        return avarages;
    }
}
