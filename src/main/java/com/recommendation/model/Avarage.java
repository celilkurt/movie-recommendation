package com.recommendation.model;

import lombok.Data;

@Data
public class Avarage implements Comparable{

    public Avarage(String id){
        this.id = id;
        avarage = 0;
    }

    private String id;
    private double avarage;

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
