package model.items;

public abstract class LibraryItem implements Comparable<LibraryItem> {
    private int stableId;
    private String title;
    private String publicationData;
    private String status;
    private int nombreDePages;

    public LibraryItem(int stableId, String title, String publicationData, String status, int nombreDePages) {
        this.stableId = stableId;
        this.title = title;
        this.publicationData = publicationData;
        this.status = status;
        this.nombreDePages=nombreDePages;
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
    public int getNombreDePages() {
        return nombreDePages;
    }
    public abstract String getDisplayInfo();

    @Override
    public int compareTo(LibraryItem second) {
        return Integer.compare(this.stableId,second.stableId);
    }
    public void setStatus(String status) {
        this.status= status;
    }

}