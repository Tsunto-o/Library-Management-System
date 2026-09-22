package model.items;

import exception.LibraryException;

import java.time.LocalDate;


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