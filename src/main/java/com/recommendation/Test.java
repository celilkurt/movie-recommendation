package com.recommendation;

import com.opencsv.CSVReader;
import com.recommendation.model.Rater;
import com.recommendation.model.Rating;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args){

        HashMap<String, Integer> wordFrequence = new HashMap<String, Integer>();

        try{
            int count = 0;
            CSVReader csvReader = new CSVReader(new FileReader("meb-okullar.csv"));
            String[] values = null;
            values = csvReader.readNext();
            while ((values = csvReader.readNext()) != null ) {
                for(String word :values[5].split(" ")){
                    if(wordFrequence.containsKey(word)){
                        wordFrequence.put(word,wordFrequence.get(word) + 1);
                    }else{
                        wordFrequence.put(word,1);
                    }
                }
            }

            System.out.println("Number of words: " + wordFrequence.size());
            wordFrequence.entrySet().forEach((Map.Entry<String,Integer> entry) -> {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            });

        }catch(Exception e){

        }


    }
}
