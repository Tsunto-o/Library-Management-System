package model.items;

import model.Borrowable;

public class Magazine extends LibraryItem implements Borrowable {
    public Magazine(int stableId, String title, String publicationData, String status) {
        super(stableId, title, publicationData, status);
    }
    @Override
    public String getDisplayInfo() {
        return "Magazine: " + getTitle()
                + " (ID: " + getStableId()
                + ", Publication date: " + getPublicationData()
                + ", Status: " + getStatus() + ")";
    }
}