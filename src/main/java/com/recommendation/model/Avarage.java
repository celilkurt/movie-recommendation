package com.recommendation.model;

import lombok.Data;

@Data
public class Avarage implements Comparable{

    private String id;
    private double avarage;
    private Movie movie;

    public Avarage(String id, Movie movie){
        this.movie = movie;
        this.id = id;
        avarage = 0;
    }

    @Override
    public int compareTo(Object o) {
        if(avarage == ((Avarage)o).avarage){
            return 0;
        }else if(avarage < ((Avarage)o).avarage){
            return -1;
        }else {
            return 1;
        }
    }
}
