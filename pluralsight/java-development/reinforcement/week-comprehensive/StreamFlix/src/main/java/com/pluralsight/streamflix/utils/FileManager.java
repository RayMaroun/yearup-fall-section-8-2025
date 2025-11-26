package com.pluralsight.streamflix.utils;

import com.pluralsight.streamflix.models.*;

import java.io.*;
import java.util.ArrayList;

public class FileManager {

    public static ArrayList<Media> loadMediaFromFile(String filename) {
        ArrayList<Media> mediaList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

/*                Media media = null;

                String[] parts = line.split(",");
                if (parts.length == 8) {
                    String type = parts[0].trim();
                    String id = parts[1].trim();
                    String title = parts[2].trim();
                    String genre = parts[3].trim();
                    int year = Integer.parseInt(parts[4].trim());
                    double rating = Double.parseDouble(parts[5].trim());

                    if (type.equalsIgnoreCase("MOVIE")) {
                        String director = parts[6].trim();
                        int duration = Integer.parseInt(parts[7].trim());
                        media = new Movie(id, title, genre, year, rating, director, duration);
                    } else if (type.equalsIgnoreCase("TVSHOW")) {
                        int seasons = Integer.parseInt(parts[6].trim());
                        int episodes = Integer.parseInt(parts[7].trim());
                        media = new TVShow(id, title, genre, year, rating, seasons, episodes);
                    }
                }

                if (media != null) {
                    mediaList.add(media);
                }*/


                Media media = parseMediaFromLine(line);
                if (media != null) {
                    mediaList.add(media);
                }
            }
            System.out.println("Loaded " + mediaList.size() + " items from " + filename);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return mediaList;
    }

    private static Media parseMediaFromLine(String line) {
        String[] parts = line.split(",");
        if (parts.length < 8) {
            return null;
        }

        String type = parts[0].trim();
        String id = parts[1].trim();
        String title = parts[2].trim();
        String genre = parts[3].trim();
        int year = Integer.parseInt(parts[4].trim());
        double rating = Double.parseDouble(parts[5].trim());

        if (type.equalsIgnoreCase("MOVIE")) {
            String director = parts[6].trim();
            int duration = Integer.parseInt(parts[7].trim());
            return new Movie(id, title, genre, year, rating, director, duration);
        } else if (type.equalsIgnoreCase("TVSHOW")) {
            int seasons = Integer.parseInt(parts[6].trim());
            int episodes = Integer.parseInt(parts[7].trim());
            return new TVShow(id, title, genre, year, rating, seasons, episodes);
        }

        return null;
    }

    public static void saveMediaToFile(ArrayList<Media> mediaList, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("type,id,title,genre,releaseYear,rating,field1,field2");
            writer.newLine();

            for (Media media : mediaList) {
/*                String mediaString = "";

                if (media instanceof Movie) {
                    Movie movie = (Movie) media;
                    mediaString = String.format("MOVIE,%s,%s,%s,%d,%.1f,%s,%d",
                            movie.getId(), movie.getTitle(), movie.getGenre(),
                            movie.getReleaseYear(), movie.getRating(),
                            movie.getDirector(), movie.getDurationMinutes());
                } else if (media instanceof TVShow) {
                    TVShow show = (TVShow) media;
                    mediaString = String.format("TVSHOW,%s,%s,%s,%d,%.1f,%d,%d",
                            show.getId(), show.getTitle(), show.getGenre(),
                            show.getReleaseYear(), show.getRating(),
                            show.getNumberOfSeasons(), show.getNumberOfEpisodes());
                }

                writer.write(mediaString);
                writer.newLine();*/


                writer.write(mediaToCSV(media));
                writer.newLine();
            }
            System.out.println("Saved " + mediaList.size() + " items to " + filename);

        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    private static String mediaToCSV(Media media) {
        if (media instanceof Movie) {
            Movie movie = (Movie) media;
            return String.format("MOVIE,%s,%s,%s,%d,%.1f,%s,%d",
                    movie.getId(), movie.getTitle(), movie.getGenre(),
                    movie.getReleaseYear(), movie.getRating(),
                    movie.getDirector(), movie.getDurationMinutes());
        } else if (media instanceof TVShow) {
            TVShow show = (TVShow) media;
            return String.format("TVSHOW,%s,%s,%s,%d,%.1f,%d,%d",
                    show.getId(), show.getTitle(), show.getGenre(),
                    show.getReleaseYear(), show.getRating(),
                    show.getNumberOfSeasons(), show.getNumberOfEpisodes());
        }
        return "";
    }
}