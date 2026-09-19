package model.items;

import java.time.LocalDate;

public abstract class LibraryItem implements Comparable<LibraryItem> {
    private int stableId;
    private String title;
    private LocalDate publicationData;
    private String status;
    private int pages;
    private String type;

    public LibraryItem(int stableId, String title, LocalDate publicationData, String status, int pages, String type) {
        this.stableId = stableId;
        this.title = title;
        this.publicationData = publicationData;
        this.status = status;
        this.pages=pages;
        this.type=type;
    }

    public String getTitle() {
        return title;
    }
    public int getStableId() {
        return stableId;
    }
    public LocalDate getPublicationData () {
        return publicationData;
    }
    public String getStatus() {
        return status;
    }
    public int getNombreDePages() {
        return pages;
    }
    public abstract String getDisplayInfo();
    public String getType() {
        return type;
    }

    @Override
    public int compareTo(LibraryItem second) {
        return Integer.compare(this.stableId,second.stableId);
    }
    public void setStatus(String status) {
        this.status= status;
    }

    public abstract void returned();
}