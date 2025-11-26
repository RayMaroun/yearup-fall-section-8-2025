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