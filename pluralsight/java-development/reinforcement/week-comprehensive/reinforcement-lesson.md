# StreamFlix Reinforcement Lab

## Introduction

Welcome to the **StreamFlix Management System** reinforcement lab! In this live coding session, you will build a streaming service management application. This project integrates the major concepts you've learned:

- **Abstract Classes & Inheritance**: Building a class hierarchy
- **Interfaces**: Defining contracts for behavior
- **Generics**: Type-safe collections
- **File I/O**: Reading and writing CSV files
- **Streams API**: Filtering and sorting data
- **Menu-Driven Applications**: Building interactive programs

---

## Step 1 - Project Setup and Package Structure

**Goal:** Set up a professional Java project structure.

Create a new Maven project named `StreamFlix` with the following package structure:
- `com.pluralsight.streamflix` (root package for main application)
- `com.pluralsight.streamflix.models` (for data classes)
- `com.pluralsight.streamflix.services` (for business logic)
- `com.pluralsight.streamflix.utils` (for utility classes)

Also create a `src/main/data` folder for the CSV file.

**Testing Checkpoint:**
- Verify all packages are created correctly

---

## Step 2 - Creating the Abstract Media Class

**Goal:** Build a base class for all media types using abstraction.

In the `models` package, create an abstract class called `Media` with:

**Fields:** `id`, `title`, `genre`, `releaseYear`, `rating`

**Methods:**
- Constructor with all fields
- Getters for all fields
- Abstract method: `getDisplayInfo()` that returns a String

**Testing Checkpoint:**
- Code compiles successfully
- Verify you cannot instantiate Media directly

---

## Step 3 - Creating the Watchable Interface

**Goal:** Define a contract for media that can be watched.

In the `models` package, create an interface called `Watchable` with:

**Methods:**
- `watch()` - void method to simulate watching
- `getWatchTime()` - returns watch time in minutes

**Testing Checkpoint:**
- Interface compiles successfully

---

## Step 4 - Creating the Movie Subclass

**Goal:** Create a concrete Movie class that extends Media and implements Watchable.

In the `models` package, create a `Movie` class that extends `Media` and implements `Watchable`:

**Additional Fields:** `director`, `durationMinutes`

**Methods:**
- Constructor (call `super()` for parent fields)
- Getters for movie-specific fields
- Implement `getDisplayInfo()` - return formatted movie info
- Implement `watch()` - print a message about watching
- Implement `getWatchTime()` - return the duration

**Testing Checkpoint:**
- Create a Movie object and call all its methods

---

## Step 5 - Creating the TVShow Subclass

**Goal:** Create a TVShow class following the same pattern as Movie.

In the `models` package, create a `TVShow` class that extends `Media` and implements `Watchable`:

**Additional Fields:** `numberOfSeasons`, `numberOfEpisodes`

**Methods:**
- Constructor (call `super()` for parent fields)
- Getters for TV show-specific fields
- Implement `getDisplayInfo()` - return formatted TV show info
- Implement `watch()` - print a message about watching
- Implement `getWatchTime()` - calculate total time (episodes × 45 minutes)

**Testing Checkpoint:**
- Create both a Movie and TVShow
- Store them in an `ArrayList<Media>` and loop through calling `getDisplayInfo()`

---

## Step 6 - Creating the Generic MediaLibrary Class

**Goal:** Build a type-safe collection manager using generics.

In the `services` package, create a class `MediaLibrary<T extends Media>` with:

**Fields:** `ArrayList<T>` to store media

**Methods:**
- `addMedia(T media)` - add to collection
- `removeMedia(String id)` - find and remove by ID
- `findMediaById(String id)` - return media or null
- `getAllMedia()` - return a copy of the list
- `size()` - return count
- `displayAll()` - loop and print each media's display info

**Testing Checkpoint:**
- Create a `MediaLibrary<Media>`
- Add Movies and TVShows
- Test `findMediaById()` and `displayAll()`

---

## Step 7 - File I/O: FileManager Class

**Goal:** Read and write media data from CSV files.

In the `utils` package, create a `FileManager` class with static methods:

**Methods:**
- `loadMediaFromFile(String filename)` - returns `ArrayList<Media>`
  - Use BufferedReader to read the file
  - Skip the header line
  - Parse each line and create Movie or TVShow based on type
  - Handle FileNotFoundException and IOException

- `saveMediaToFile(ArrayList<Media> mediaList, String filename)` - void
  - Use BufferedWriter to write the file
  - Write header line first
  - Convert each media to CSV format
  - Handle IOException

**CSV Format:** `type,id,title,genre,releaseYear,rating,field1,field2`
- Movies: field1=director, field2=duration
- TV Shows: field1=seasons, field2=episodes

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

**Testing Checkpoint:**
- Load media from CSV and verify the count
- Verify both movies and TV shows load correctly

---

## Step 8 - Main Application with Streams

**Goal:** Create the main application and use streams for filtering and sorting.

Create `StreamFlixApp.java` in the root package with:

**Fields:**
- `static Scanner scanner`
- `static MediaLibrary<Media> library`
- `static final String DATA_FILE` (path to CSV)

**Methods:**
- `main()` - load data, then demonstrate streams
- `loadData()` - use FileManager to load CSV into library

**Streams Demo** (in main or separate method):
- Filter by genre: use `.stream().filter()` to find all "Sci-Fi" media
- Sort by rating: use `.stream().sorted()` to order by rating (highest first)
- Print results

**Testing Checkpoint:**
- Run and verify filtering shows only Sci-Fi items
- Verify sorting displays highest rated first

---

## Step 9 - Adding the Menu Loop

**Goal:** Create a menu-driven interface for the application.

Add to `StreamFlixApp`:

**Method: `mainMenu()`**
- Use a boolean flag and while loop
- Print menu options:
  ```
  1. View All Media
  2. Add New Media
  3. Search Media
  0. Exit
  ```
- Read user choice with Scanner
- Use switch statement to call appropriate method
- Catch NumberFormatException for invalid input

**Method: `saveData()`**
- Call FileManager.saveMediaToFile()

**Update `main()`** to call: loadData(), mainMenu(), saveData()

**Testing Checkpoint:**
- Menu displays and loops correctly
- Invalid input shows error message
- Exit option breaks the loop

---

## Step 10 - Implementing Menu Methods

**Goal:** Complete the application by implementing all menu options.

Add these methods to `StreamFlixApp`:

**Method: `viewAllMedia()`**
- Print header with count
- Call `library.displayAll()`

**Method: `addNewMedia()`**
- Ask for type (1=Movie, 2=TV Show)
- Prompt for: id, title, genre, year, rating
- If Movie: prompt for director, duration
- If TV Show: prompt for seasons, episodes
- Create object and add to library
- Wrap in try-catch for invalid input

**Method: `searchMedia()`**
- Prompt for keyword
- Use streams to filter where title OR genre contains keyword (case-insensitive)
- Display results or "No results found"

**Testing Checkpoint:**
- Test complete flow:
  1. View all media
  2. Add a new movie
  3. Search for the movie you added
  4. Exit (saves data)
  5. Restart and verify new movie persisted

---

## Final Summary

You've built a complete Java application demonstrating:

| Concept | Where Used |
|---------|------------|
| Package Organization | All classes in proper packages |
| Abstract Classes | Media class |
| Inheritance | Movie, TVShow extend Media |
| Interfaces | Watchable interface |
| Polymorphism | Media references to different types |
| Generics | MediaLibrary<T extends Media> |
| File I/O | FileManager read/write |
| Exception Handling | File operations, user input |
| Streams API | Filtering and sorting |
| Lambda Expressions | filter(), sorted() |

---

## Extension Ideas (Bonus)

If you want to practice more:

1. Add a "Filter by Genre" menu option that prompts for a genre
2. Add a "View Top Rated" option that shows the top N rated media
3. Add statistics (total count, average rating)
4. Add input validation (duplicate IDs, rating range 0-10)
