package model.items;


public class Book extends LibraryItem implements Borrowable {
    private String author;
    private String isbn;
    private String genre;

    public Book(int stableId, String title, String publicationData, String status, String author, String isbn, String genre,int pages) {
        super(stableId, title, publicationData, status,pages);
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
        return "Book: " + getTitle() + " by " + author + " (ID: " + getStableId()
                + ", ISBN: " + isbn + ", Pages: " + getNombreDePages()
                + ", Publication: " + getPublicationData() + ", Status: " + getStatus() + ")";



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
    public void borrow() {
        if (this.isAvailable()) {
            this.setStatus("BORROWED");
        }
        else {
            System.out.println("The book must be available!");
        }
    }

}