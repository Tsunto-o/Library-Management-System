package model.items;

public interface Borrowable {
    public void returned();
    public boolean isAvailable();
    public void borrow();
}
