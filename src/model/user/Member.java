package model.user;

import java.time.LocalDate;

public class Member extends User {
    private int activeLoanLimit;

    public Member(String stableId, String firstName, String lastName, LocalDate birthdayDate, int activeLoanLimit) {
        super("MEMBER", stableId, firstName, lastName, birthdayDate);
        this.activeLoanLimit = activeLoanLimit;
    }
    @Override
    public String getDisplayInfo() {
        return "Member: " + getFirstName() + " | Last name: " + getLastName() + " | ID: " + getStableId() +" | Limit: " + activeLoanLimit + " items";
    }
}