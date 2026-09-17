package model.items;

public class Magazine extends LibraryItem implements Borrowable {
    public Magazine(int stableId, String title, String publicationData, String status, int pages) {
        super(stableId, title, publicationData, status, pages);
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

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public void borrow() {

    }
}