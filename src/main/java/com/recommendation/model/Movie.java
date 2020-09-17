package com.recommendation.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Movie {

    private String id;
    private String title;
    private int year;
    private String genres;
    private String director;
    private String country;
    private String poster;
    private int minutes;

    public Movie(String id, String title, String year, String genres) {
        this.id = id.trim();
        this.title = title.trim();
        this.year = Integer.parseInt(year.trim());
        this.genres = genres;
    }

    public Movie(String id, String title, String year, String genres, String director, String country, String poster, String minutes) {
        this.id = id.trim();
        this.title = title.trim();
        this.year = Integer.parseInt(year.trim());
        this.genres = genres;
        this.director = director;
        this.country = country;
        this.poster = poster;
        this.minutes = Integer.parseInt(minutes.trim());
    }

    @Override
    public String toString() {
        return "entity.Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", genres='" + genres + '\'' +
                ", director='" + director + '\'' +
                ", country='" + country + '\'' +
                ", poster='" + poster + '\'' +
                ", minutes=" + minutes +
                '}';
    }
}
