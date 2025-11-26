package com.pluralsight.streamflix.models;

public class TVShow extends Media implements Watchable {
    private int numberOfSeasons;
    private int numberOfEpisodes;

    public TVShow(String id, String title, String genre, int releaseYear, double rating,
                  int numberOfSeasons, int numberOfEpisodes) {
        super(id, title, genre, releaseYear, rating);
        this.numberOfSeasons = numberOfSeasons;
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    @Override
    public String getDisplayInfo() {
        return String.format("TV SHOW: %s (%d) | %s | Rating: %.1f | %d seasons, %d episodes",
                getTitle(), getReleaseYear(), getGenre(), getRating(), numberOfSeasons, numberOfEpisodes);
    }

    @Override
    public void watch() {
        System.out.println("Starting: " + getTitle());
        System.out.println(numberOfSeasons + " seasons, " + numberOfEpisodes + " episodes");
    }

    @Override
    public int getWatchTime() {
        return numberOfEpisodes * 45; // 45 minutes per episode
    }
}