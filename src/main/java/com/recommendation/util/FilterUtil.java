package com.recommendation.util;

import com.recommendation.Database;
import com.recommendation.model.Avarage;
import com.recommendation.model.Movie;
import com.recommendation.filter.Filter;

import java.util.*;

public class FilterUtil {

    private ArrayList<Filter> filters;
    private int minRater = 0;

    public FilterUtil(int minRater, Filter... filters){
        this.minRater = minRater;
        this.filters = new ArrayList<Filter>();
        this.filters.addAll(Arrays.asList(filters));
    }

    public HashMap<String,Movie> getMoviesByFilters(HashMap<String,Movie> movies){
        for(Filter filter: filters){
            movies = filter.getMoviesByFilter(movies,minRater);
        }
        return movies;
    }

    public ArrayList<Avarage> getAvaragesByFilters(HashMap<String,Movie> movies){

        movies = getMoviesByFilters(movies);
        return getAvarages(movies);
    }

    public ArrayList<Avarage> getAvarages(HashMap<String,Movie> movies) {


        ArrayList<Avarage> avarages = new ArrayList<>();
        for(Map.Entry<String,Movie> movie: movies.entrySet()){
            Avarage avarage = new Avarage(movie.getKey(), movie.getValue());
            double sum = 0;
            int count = 0;

            if(Database.getInstance().getRatings().containsKey(movie.getKey())){
                for(Double rating: Database.getInstance().getRatings().get(movie.getKey())){
                    sum+=rating;
                    count++;
                }
            }else{
                count = 1;
            }

            avarage.setAvarage(sum/count);
            avarages.add(avarage);
        }
        Collections.sort(avarages);

        return avarages;
    }

    //Bütün filtrelerle uyumluysa true döner
    public boolean isMatchById(String movieId){
        for(Filter filter :filters){
            if(!filter.isMatch(movieId)){
                return false;
            }
        }
        return true;
    }
}
