package com.pluralsight.streamflix.models;

public class Movie extends Media implements Watchable {
    private String director;
    private int durationMinutes;

    public Movie(String id, String title, String genre, int releaseYear, double rating,
                 String director, int durationMinutes) {
        super(id, title, genre, releaseYear, rating);
        this.director = director;
        this.durationMinutes = durationMinutes;
    }

    public String getDirector() {
        return director;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    @Override
    public String getDisplayInfo() {
        return String.format("MOVIE: %s (%d) | %s | Rating: %.1f | Director: %s | %d min",
                getTitle(), getReleaseYear(), getGenre(), getRating(), director, durationMinutes);
    }

    @Override
    public void watch() {
        System.out.println("Now watching: " + getTitle());
        System.out.println("Directed by " + director + " | " + durationMinutes + " minutes");
    }

    @Override
    public int getWatchTime() {
        return durationMinutes;
    }
}