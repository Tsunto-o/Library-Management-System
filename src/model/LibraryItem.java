package model;

import java.util.Comparator;

public abstract class LibraryItem implements Comparator<LibraryItem> {
    private int stableId;
    private String title;
    private String publicationData;
    private String status;

    public String getTitle() {
        return title;
    }
    public int getStableId() {
        return stableId;
    }
    public String getPublicationData () {
        return publicationData;
    }
    public String getStatus() {
        return status;
    }
    public abstract String getDisplayInfo();

}