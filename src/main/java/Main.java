import entity.Movie;
import entity.Rater;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static ArrayList<Movie> movies;
    private static ArrayList<Rater> raters;

    public static void main(String[] args){

        movies = new ArrayList<Movie>();
        raters = new ArrayList<Rater>();

        try {
            Scanner scanner = new Scanner(new File("ratedmovies_short.csv"));
            scanner.nextLine();
            //id,title,year,country,genre,director,minutes,poster
            while (scanner.hasNext()) {
                String[] values = scanner.nextLine().split(",");
                movies.add(new Movie(values[0],values[1],values[2],values[4],values[5],values[3],values[7],values[6]));
            }
            for(Movie movie: movies)
                System.out.println(movie.toString());
            scanner.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Scanner scanner = new Scanner(new File("ratings_short.csv"));
            scanner.nextLine();
            while (scanner.hasNext()) {
                String[] values = scanner.nextLine().split(",");
                System.out.println("rater_id: " + values[0] + ", movie_id: " + values[1] + ", rating: "+ values[2] +", time: " + values[3]);
            }
            scanner.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }



}
