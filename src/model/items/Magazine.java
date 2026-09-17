package model.items;

import exception.LibraryException;

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
    public void returnItem() {
        if (getStatus().equals("BORROWED")) {
            setStatus("AVAILABLE");
            System.out.println("The magazine has been returned. Thank You!");

        }
        else {
            throw new LibraryException("You can't return a book not borrowed!");
        }

    }

    @Override
    public boolean isAvailable() {
        if (getStatus().equals("AVAILABLE")) {
            return true;
        }
        return false;
    }

    @Override
    public void borrow() {
        if (this.isAvailable()) {
            this.setStatus("BORROWED");
        } else {
            throw new LibraryException("This magazine is currently borrowed and unavailable");
        }

    }
}