package model.people;

import java.time.LocalDate;

public class Librarian extends User {
    public Librarian(String stableId, String firstName, String lastName, LocalDate birthdayDate) {
        super(stableId, firstName, lastName, birthdayDate, "LIBRARIAN");

    }
    @Override
    public String getDisplayInfo() {
        return "Librarian: " + getFirstName() + " " + getLastName() + " (ID: " + getStableId() + ") — can manage catalogue and loans";
    }
}
