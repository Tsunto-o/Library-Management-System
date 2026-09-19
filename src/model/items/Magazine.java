package model.items;

import exception.LibraryException;

import java.time.LocalDate;

/**
 * Magazines are a type of thin book with large pages and a paper cover that contains articles and photographs.
 * They can not be borrowed since their thinness makes them fragile
 */
public class Magazine extends LibraryItem {
    public Magazine(int stableId, String title, LocalDate publicationData, String status, int pages) {
        super("MAGAZINE", stableId, title, publicationData, status, pages);
    }
    @Override
    public String getDisplayInfo() {
        return "Magazine: " + getTitle()
                + " (ID: " + getStableId()
                + ", Publication date: " + getPublicationData()
                + ", Status: " + getStatus() + ")";
    }

    @Override
    public void returned() {

    }


}