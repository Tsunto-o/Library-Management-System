package model.user;

import java.time.LocalDate;
import model.Identifiable;

public abstract class User implements Comparable<User>, Identifiable {
    private String rolePermissions;
    private String stableId;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthdayDate;



    public User(String rolePermissions, String stableId, String password, String firstName,String lastName,LocalDate birthdayDate) {
        this.rolePermissions = rolePermissions;
        this.stableId = stableId;
        this.password = password;
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
    public String getPassword() {return password;}
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

    @Override
    public String getIdentifier() {
        return getStableId();
    }
}