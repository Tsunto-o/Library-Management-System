package model.user;

import java.time.LocalDate;

public class Librarian extends User {
    public Librarian(String stableId, String password, String firstName, String lastName, LocalDate birthdayDate) {
        super("LIBRARIAN", stableId, password, firstName, lastName, birthdayDate);

    }
    @Override
    public String getDisplayInfo() {
        return "Librarian: " + getFirstName() + " | Last name: " + getLastName() + " | ID: " + getStableId();
    }
}
