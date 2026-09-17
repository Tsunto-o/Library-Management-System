package model.people;

import java.time.LocalDate;

public abstract class User implements Comparable<User> {
    private String stableId;
    private String firstName;
    private String rolePermissions;
    private LocalDate birthdayDate;
    private String lastName;

    public User(String stableId,String firstName,String lastName,LocalDate birthdayDate, String rolePermissions) {
        this.stableId = stableId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdayDate = birthdayDate;
        this.rolePermissions = rolePermissions;
    }

    public abstract String getDisplayInfo();

    @Override
    public String toString() {
        return "Id: "+stableId+"First name: "+firstName+" Last name: "+lastName+" Role permissions: "+rolePermissions;
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
    public String getRolePermissions() {
        return rolePermissions;
    }

    @Override
    public int compareTo(User second) {
        return this.lastName.compareTo(second.lastName);
    }
}