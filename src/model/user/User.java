package model.user;

import java.time.LocalDate;

public abstract class User implements Comparable<User> {
    private String rolePermissions;
    private String stableId;
    private String firstName;
    private String lastName;
    private LocalDate birthdayDate;



    public User(String rolePermissions, String stableId, String firstName,String lastName,LocalDate birthdayDate) {
        this.rolePermissions = rolePermissions;
        this.stableId = stableId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdayDate = birthdayDate;
    }

    public String getRolePermissions() {
        return rolePermissions;
    }
    public String getStableId() {
        return stableId;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName () {
        return lastName;
    }
    public LocalDate getBirthdayDate() { return birthdayDate; }

    public abstract String getDisplayInfo();



    @Override
    public String toString() {
        return "Role permissions: " + rolePermissions + " - Id: " + stableId + " - First name: " + firstName + " - Last name: " + lastName + "\n";
    }

    @Override
    public int compareTo(User second) {
        return this.lastName.compareTo(second.lastName);
    }
}