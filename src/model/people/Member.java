package model.people;

import java.time.LocalDate;

public class Member extends User {
    private int activeLoanLimit;

    public Member(String stableId, String firstName, String lastName, LocalDate birthdayDate,int activeLoanLimit) {
        super(stableId, firstName, lastName, birthdayDate, "MEMBER");
        this.activeLoanLimit=activeLoanLimit;
    }
    @Override
    public String getDisplayInfo() {
        return "Member: " + getFirstName() + " " + getLastName() + " (ID: " + getStableId() + ")" +" limit: "+ activeLoanLimit+" items";

    }
}