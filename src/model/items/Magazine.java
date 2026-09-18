package model.items;

import exception.LibraryException;

public class Magazine extends LibraryItem {
    public Magazine(int stableId, String title, String publicationData, String status, int pages) {
        super(stableId, title, publicationData, status, pages,"MAGAZINE");
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