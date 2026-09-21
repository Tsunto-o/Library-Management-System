package model.user;

import java.time.LocalDate;

public class Librarian extends User {
    public Librarian(String stableId, String firstName, String lastName, LocalDate birthdayDate) {
        super("LIBRARIAN", stableId, firstName, lastName, birthdayDate);

    }
    @Override
    public String getDisplayInfo() {
        return "Librarian: " + getFirstName() + " " + getLastName() + " (ID: " + getStableId() + ") — can manage catalogue and loans";
    }
}
