package model.items;

import exception.LibraryException;

import java.time.LocalDate;


public class Book extends LibraryItem implements Borrowable {
    private String author;
    private String isbn;
    private String genre;

    public Book(int stableId, String title, LocalDate publicationData, String status, String author, String isbn, String genre, int pages) {
        super("BOOK", stableId, title, publicationData, status, pages);
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;

    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String getDisplayInfo() {
        return "Book: " + getTitle() + " by " + this.author + " | (ID: " + this.getStableId()
                + " | ISBN: " + this.isbn + " | Pages: " + this.getNombreDePages()
                + " | Publication: " + this.getPublicationData() + " | Status: " + this.getStatus() + ")";


    }

    @Override
    public void returned() {

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
            throw new LibraryException("This book is currently borrowed and unavailable");
        }
    }

    @Override
    public void returnItem() {
        if (getStatus().equals("BORROWED")) {
            this.setStatus("AVAILABLE");
            System.out.println("The book has been returned. Thank You!");
        } else {
            throw new LibraryException("You can't return a book not borrowed!");
        }
    }

}


