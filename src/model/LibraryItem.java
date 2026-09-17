package model;

import java.util.Comparator;

public abstract class LibraryItem implements Comparable<LibraryItem> {
    private int stableId;
    private String title;
    private String publicationData;
    private String status;

    public LibraryItem(int stableId, String title, String publicationData, String status) {
        this.stableId = stableId;
        this.title = title;
        this.publicationData = publicationData;
        this.status = status;
    }

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

    @Override
    public int compareTo(LibraryItem second) {
        return Integer.compare(this.stableId,second.stableId);
    }

}