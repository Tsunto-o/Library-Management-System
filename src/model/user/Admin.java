package model.user;

import java.time.LocalDate;

public class Admin extends User {
    public Admin(String stableId, String password, String firstName, String lastName, LocalDate birthdayDate) {
        super("ADMIN", stableId, password, firstName, lastName, birthdayDate);
    }

    @Override
    public String getDisplayInfo() {
        return "Member: " + getFirstName() + " | Last name: " + getLastName() + " | ID: " + getStableId();
    }
}
