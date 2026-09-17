package model.people;

import java.time.LocalDate;

public class Admin extends User {
    public Admin(String stableId, String firstName, String lastName, LocalDate birthdayDate) {
        super(stableId, firstName, lastName, birthdayDate, "ADMIN");

    }

    @Override
    public String getDisplayInfo() {
        return "Admin: " + getFirstName() + " " + getLastName() + " (ID: " + getStableId() + ") — full system access";

    }
}
