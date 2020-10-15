package com.recommendation.filter;

import com.recommendation.Database;
import com.recommendation.model.Movie;

import java.util.*;

public class DirectorFilter extends Filter<String[]> {

    public DirectorFilter(String query) {
        super(query.toLowerCase().split(","));
        for(int i = 0; i < this.query.length; i++){
            this.query[i] = this.query[i].trim();
        }
    }

    @Override
    public HashMap<String,Movie> getMoviesByFilter(HashMap<String,Movie> movies, int minRater) {


        HashMap<String,Movie> resultList = new HashMap<>();
        for(Movie movie: movies.values()){
            if(Database.getInstance().getRatings().get(movie.getId()).size() >= minRater){
                if(isMatch(movie.getId())){
                    resultList.put(movie.getId(),movie);
                }
            }
        }
        return resultList;
    }

    @Override
    public boolean isMatch(String movieID) {
        String[] queries = this.query;
        String[] directors = Database.getInstance().getMovieById(movieID).getDirector().split(",");

        for(String query: queries){
            for(String director: directors){
                if(query.equals(director.toLowerCase().trim())){
                    return true;
                }
            }
        }
        return false;
    }

}
