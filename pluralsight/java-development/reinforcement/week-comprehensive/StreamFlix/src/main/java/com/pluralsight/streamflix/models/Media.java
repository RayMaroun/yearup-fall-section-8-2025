package com.pluralsight.streamflix.models;

public abstract class Media {
    private String id;
    private String title;
    private String genre;
    private int releaseYear;
    private double rating;

    public Media(String id, String title, String genre, int releaseYear, double rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public double getRating() {
        return rating;
    }

    public abstract String getDisplayInfo();

    @Override
    public String toString() {
        return String.format("%s (%d) - %s - Rating: %.1f", title, releaseYear, genre, rating);
    }
}