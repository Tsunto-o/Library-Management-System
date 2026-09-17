package model.items;

public interface Borrowable {
    public void returnItem();
    public boolean isAvailable();
    public void borrow();

    }
