# StreamFlix Management System - Solutions

This file contains step-by-step solutions for the StreamFlix reinforcement lab.

---

## Step 1 - Project Setup and Package Structure

**Solution:**

Create a new Maven project named `StreamFlix` with this structure:

```
src/main/java/
└── com/
    └── pluralsight/
        └── streamflix/
            ├── models/
            ├── services/
            └── utils/
```

Also create `src/main/data/` for the CSV file.

---

## Step 2 - Creating the Abstract Media Class

**Solution:**

Create `Media.java` in `com.pluralsight.streamflix.models`:

```java
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
```

---

## Step 3 - Creating the Watchable Interface

**Solution:**

Create `Watchable.java` in `com.pluralsight.streamflix.models`:

```java
package com.pluralsight.streamflix.models;

public interface Watchable {
    void watch();
    int getWatchTime();
}
```

---

## Step 4 - Creating the Movie Subclass

**Solution:**

Create `Movie.java` in `com.pluralsight.streamflix.models`:

```java
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
```

---

## Step 5 - Creating the TVShow Subclass

**Solution:**

Create `TVShow.java` in `com.pluralsight.streamflix.models`:

```java
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
```

**Test polymorphism:**

```java
ArrayList<Media> mediaList = new ArrayList<>();
mediaList.add(new Movie("M001", "Inception", "Sci-Fi", 2010, 8.8, "Christopher Nolan", 148));
mediaList.add(new TVShow("T001", "Breaking Bad", "Crime", 2008, 9.5, 5, 62));

for (Media media : mediaList) {
    System.out.println(media.getDisplayInfo());
}
```

---

## Step 6 - Creating the Generic MediaLibrary Class

**Solution:**

Create `MediaLibrary.java` in `com.pluralsight.streamflix.services`:

```java
package com.pluralsight.streamflix.services;

import com.pluralsight.streamflix.models.Media;
import java.util.ArrayList;

public class MediaLibrary<T extends Media> {
    private ArrayList<T> mediaCollection;

    public MediaLibrary() {
        this.mediaCollection = new ArrayList<>();
    }

    public void addMedia(T media) {
        mediaCollection.add(media);
        System.out.println("Added: " + media.getTitle());
    }

    public void removeMedia(String id) {
        T toRemove = findMediaById(id);
        if (toRemove != null) {
            mediaCollection.remove(toRemove);
            System.out.println("Removed: " + toRemove.getTitle());
        } else {
            System.out.println("Media with ID " + id + " not found");
        }
    }

    public T findMediaById(String id) {
        for (T media : mediaCollection) {
            if (media.getId().equals(id)) {
                return media;
            }
        }
        return null;
    }

    public ArrayList<T> getAllMedia() {
        return new ArrayList<>(mediaCollection);
    }

    public int size() {
        return mediaCollection.size();
    }

    public void displayAll() {
        if (mediaCollection.isEmpty()) {
            System.out.println("No media in library");
            return;
        }
        for (int i = 0; i < mediaCollection.size(); i++) {
            System.out.println((i + 1) + ". " + mediaCollection.get(i).getDisplayInfo());
        }
    }
}
```

---

## Step 7 - File I/O: FileManager Class

**Solution:**

Create `FileManager.java` in `com.pluralsight.streamflix.utils`:

```java
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
```

**Sample media.csv** (create in `src/main/data/`):

```
type,id,title,genre,releaseYear,rating,field1,field2
MOVIE,M001,The Shawshank Redemption,Drama,1994,9.3,Frank Darabont,142
MOVIE,M002,Inception,Sci-Fi,2010,8.8,Christopher Nolan,148
MOVIE,M003,The Dark Knight,Action,2008,9.0,Christopher Nolan,152
MOVIE,M004,The Matrix,Sci-Fi,1999,8.7,The Wachowskis,136
TVSHOW,T001,Breaking Bad,Crime,2008,9.5,5,62
TVSHOW,T002,Stranger Things,Sci-Fi,2016,8.7,4,34
TVSHOW,T003,Game of Thrones,Fantasy,2011,9.2,8,73
TVSHOW,T004,The Office,Comedy,2005,9.0,9,201
```

---

## Step 8 - Main Application with Streams

**Solution:**

Create `StreamFlixApp.java` in `com.pluralsight.streamflix`:

```java
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
        demonstrateStreams();
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
```

---

## Step 9 - Adding the Menu Loop

**Solution:**

Update `StreamFlixApp.java` - replace `main` and add `mainMenu`:

```java
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

    // Placeholder methods for Step 10
    private static void viewAllMedia() {
        System.out.println("View All - TODO");
    }

    private static void addNewMedia() {
        System.out.println("Add New - TODO");
    }

    private static void searchMedia() {
        System.out.println("Search - TODO");
    }
```

---

## Step 10 - Implementing Menu Methods

**Solution:**

Complete the menu methods in `StreamFlixApp.java`:

```java
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
```

---

## Complete StreamFlixApp.java

Here's the final complete file:

```java
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

    private static void loadData() {
        ArrayList<Media> loadedMedia = FileManager.loadMediaFromFile(DATA_FILE);
        for (Media media : loadedMedia) {
            library.addMedia(media);
        }
        System.out.println();
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

    private static void saveData() {
        FileManager.saveMediaToFile(library.getAllMedia(), DATA_FILE);
    }
}
```

---

## Summary

| Step | File               | Key Concept                 |
| ---- | ------------------ | --------------------------- |
| 1    | Project setup      | Package organization        |
| 2    | Media.java         | Abstract classes            |
| 3    | Watchable.java     | Interfaces                  |
| 4    | Movie.java         | Inheritance + interfaces    |
| 5    | TVShow.java        | Polymorphism                |
| 6    | MediaLibrary.java  | Generics                    |
| 7    | FileManager.java   | File I/O                    |
| 8-10 | StreamFlixApp.java | Streams, menus, application |
