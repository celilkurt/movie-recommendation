import com.opencsv.CSVReader;
import entity.Movie;
import entity.Rater;
import entity.Rating;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static ArrayList<Movie> movies;
    private static ArrayList<Rater> raters;

    public static void main(String[] args){

        movies = new ArrayList<Movie>();
        raters = new ArrayList<Rater>();

        fetchMoviesByFileName(movies,"ratedmoviesfull.csv");

        fetchRatingsByFileName(raters,"ratings.csv");

        findNumberOfFilmByGenre(movies,"Comedy");
        findNumberOfFilmLargerThanByMinutes(movies,150);
        findMaxNumOfFilmDirectedByAnyDirector(movies);
        findDirectorMostFilmDirected(movies);
        findNumOfRatedUniqueFilm(raters);
        findNumberOfRatingWithRaterID(raters,"193");
        findNumberOfRatingWithMovieID(raters,"1798709");

    }

    public static void fetchRatingsByFileName(ArrayList<Rater> raters, String fileName){

        try{
            CSVReader csvReader = new CSVReader(new FileReader("ratings.csv"));
            String[] values = null;
            values = csvReader.readNext();
            while ((values = csvReader.readNext()) != null) {
                String id = values[0].trim();
                Rater rater = findRaterWithID(raters, id);

                if(rater == null){
                    rater = new Rater(values[0].trim());
                    raters.add(rater);
                }

                rater.addRating(values[1],Double.parseDouble(values[2]));
            }
            int maxRatings = 0;
            String id = "";
            for(Rater rater: raters){
                if(rater.numRatings() > maxRatings){
                    maxRatings = rater.numRatings();
                    id = rater.getID();
                }
                //System.out.println("Number of rates user with '" + rater.getID() + "' id: " + rater.numRatings());
            }
            System.out.println("id: " + id + "maxRatings: " + maxRatings);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void fetchMoviesByFileName(ArrayList<Movie> movies, String fileName){
        try{
            CSVReader csvReader = new CSVReader(new FileReader(fileName));
            String[] values = null;
            values = csvReader.readNext();
            while ((values = csvReader.readNext()) != null) {
                movies.add(new Movie(values[0],values[1],values[2],values[4],values[5],values[3],values[7],values[6]));
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int findNumberOfFilmByGenre(ArrayList<Movie> movies,String genre){

        int count = 0;
        for(Movie movie: movies){
            if(movie.getGenres().toLowerCase().contains(genre.toLowerCase())){
                count++;
            }
        }
        return count;
    }

    public static int findNumberOfFilmLargerThanByMinutes(ArrayList<Movie> movies, int minute){
        int count = 0;
        for(Movie movie:movies){

            if(movie.getMinutes() > minute){
                count++;
            }
        }

        System.out.println("Count: " + count);
        return count;
    }

    public static int findMaxNumOfFilmDirectedByAnyDirector(ArrayList<Movie> movies){

        HashMap<String,Integer> numOfDirectorFilms = new HashMap<String, Integer>();
        for(Movie movie:movies){

            String[] directors = movie.getDirector().split(",");
            for(String director: directors){
                if(numOfDirectorFilms.containsKey(director)){
                    numOfDirectorFilms.put(director,numOfDirectorFilms.get(director) + 1);
                }else{
                    numOfDirectorFilms.put(director,1);
                }
            }
        }

        String director = "";
        int max = 0;

        for(Map.Entry<String,Integer> entry: numOfDirectorFilms.entrySet()){
            if(entry.getValue() > max){
                max = entry.getValue();
                director = entry.getKey();
            }
        }

        System.out.println(director + " " + max);
        return max;
    }

    public static String findDirectorMostFilmDirected(ArrayList<Movie> movies){
        //What is the maximum number of films directed by one director?
        HashMap<String,Integer> numOfDirectorFilms = new HashMap<String, Integer>();
        for(Movie movie:movies){

            String[] directors = movie.getDirector().split(",");
            for(String director: directors){
                if(numOfDirectorFilms.containsKey(director)){
                    numOfDirectorFilms.put(director,numOfDirectorFilms.get(director) + 1);
                }else{
                    numOfDirectorFilms.put(director,1);
                }
            }
        }

        String director = "";
        int max = 0;

        for(Map.Entry<String,Integer> entry: numOfDirectorFilms.entrySet()){
            if(entry.getValue() > max){
                max = entry.getValue();
                director = entry.getKey();
            }
        }

        System.out.println(director + " " + max);
        return director;
    }

    public static int findNumberOfRatingWithRaterID(ArrayList<Rater> raters, String id){
        int count = findRaterWithID(raters,id).numRatings();
        System.out.println(count);
        return count;
    }



    public static int findNumberOfRatingWithMovieID(ArrayList<Rater> raters, String id){

        int count = 0;
        for(Rater rater: raters){

            if(rater.getRating(id) != -1){
                count++;
            }
        }

        System.out.println( id + ":     " + count);
        return count;

    }



    public static int findNumOfRatedUniqueFilm(ArrayList<Rater> raters){
        //What is the total number of unique movies that have been rated?

        HashMap<String,Integer> numOfFilmRates = new HashMap<String, Integer>();

        for(Rater rater: raters){
            for(Rating rating: rater.getMyRatings()){
                if(numOfFilmRates.containsKey(rating.getItem())){
                    numOfFilmRates.put(rating.getItem(),numOfFilmRates.get(rating.getItem()) + 1);
                }else{
                    numOfFilmRates.put(rating.getItem(),1);
                }
            }
        }


        int count = 0;

        for(Map.Entry<String,Integer> entry: numOfFilmRates.entrySet()){
            if(entry.getValue() == 1){
                count++;
            }
        }

        System.out.println("count of unique: " + count);

        return count;
    }


    public static Rater findRaterWithID(ArrayList<Rater> raters, String id){
        for(Rater rater: raters){
            if(rater.getID().equals(id)){
                return rater;
            }
        }
        return null;
    }

    public static Movie findMovieWithID(ArrayList<Movie> movies, String id){
        for(Movie movie: movies){
            if(movie.getId().equals(id)){
                return movie;
            }
        }
        return null;
    }



}
