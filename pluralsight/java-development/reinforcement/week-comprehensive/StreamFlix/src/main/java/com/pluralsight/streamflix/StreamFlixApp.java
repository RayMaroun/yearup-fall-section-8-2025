package com.pluralsight.streamflix;

import com.pluralsight.streamflix.models.*;
import com.pluralsight.streamflix.services.MediaLibrary;
import com.pluralsight.streamflix.utils.FileManager;

import java.util.*;
import java.util.stream.Collectors;

public class StreamFlixApp {
    private static Scanner scanner = new Scanner(System.in);
    private static MediaLibrary<Media> library = new MediaLibrary<>();
    private static final String DATA_FILE = "src/main/data/media.csv";

    public static void main(String[] args) {
        System.out.println("=== STREAMFLIX ===\n");
        loadData();
        mainMenu();
        saveData();
        System.out.println("\nGoodbye!");
    }

    private static void mainMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. View All Media");
            System.out.println("2. Add New Media");
            System.out.println("3. Search Media");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        viewAllMedia();
                        break;
                    case 2:
                        addNewMedia();
                        break;
                    case 3:
                        searchMedia();
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number");
            }
        }
    }

    private static void saveData() {
        FileManager.saveMediaToFile(library.getAllMedia(), DATA_FILE);
    }

    private static void viewAllMedia() {
        System.out.println("\n--- All Media (" + library.size() + " items) ---");
        library.displayAll();
    }

    private static void addNewMedia() {
        System.out.println("\n--- Add New Media ---");
        System.out.println("1. Movie");
        System.out.println("2. TV Show");
        System.out.print("Type: ");

        try {
            int type = Integer.parseInt(scanner.nextLine());

            System.out.print("ID: ");
            String id = scanner.nextLine();

            System.out.print("Title: ");
            String title = scanner.nextLine();

            System.out.print("Genre: ");
            String genre = scanner.nextLine();

            System.out.print("Release Year: ");
            int year = Integer.parseInt(scanner.nextLine());

            System.out.print("Rating (0-10): ");
            double rating = Double.parseDouble(scanner.nextLine());

            if (type == 1) {
                System.out.print("Director: ");
                String director = scanner.nextLine();

                System.out.print("Duration (minutes): ");
                int duration = Integer.parseInt(scanner.nextLine());

                library.addMedia(new Movie(id, title, genre, year, rating, director, duration));
            } else if (type == 2) {
                System.out.print("Number of Seasons: ");
                int seasons = Integer.parseInt(scanner.nextLine());

                System.out.print("Number of Episodes: ");
                int episodes = Integer.parseInt(scanner.nextLine());

                library.addMedia(new TVShow(id, title, genre, year, rating, seasons, episodes));
            } else {
                System.out.println("Invalid type");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
        }
    }

    private static void searchMedia() {
        System.out.print("\nEnter search keyword: ");
        String keyword = scanner.nextLine().toLowerCase();

        List<Media> results = library.getAllMedia().stream()
                .filter(media ->
                        media.getTitle().toLowerCase().contains(keyword) ||
                                media.getGenre().toLowerCase().contains(keyword))
                .collect(Collectors.toList());

        System.out.println("\n--- Search Results (" + results.size() + " found) ---");
        if (results.isEmpty()) {
            System.out.println("No results found");
        } else {
            for (Media media : results) {
                System.out.println(media.getDisplayInfo());
            }
        }
    }


    private static void loadData() {
        ArrayList<Media> loadedMedia = FileManager.loadMediaFromFile(DATA_FILE);
        for (Media media : loadedMedia) {
            library.addMedia(media);
        }
        System.out.println();
    }

    private static void demonstrateStreams() {
        System.out.println("--- Sci-Fi Content ---");
        List<Media> sciFi = library.getAllMedia().stream()
                .filter(media -> media.getGenre().equalsIgnoreCase("Sci-Fi"))
                .collect(Collectors.toList());

        for (Media media : sciFi) {
            System.out.println(media.getDisplayInfo());
        }

        System.out.println("\n--- Top Rated (sorted) ---");
        List<Media> sorted = library.getAllMedia().stream()
                .sorted((m1, m2) -> Double.compare(m2.getRating(), m1.getRating()))
                .collect(Collectors.toList());

        for (Media media : sorted) {
            System.out.println(media.getDisplayInfo());
        }
    }
}