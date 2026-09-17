package model;

public abstract class User implements Comparable<User> {
    private int stableId;
    private String name;
    private String rolePermissions;

    public User(int stableId,String name, String rolePermissions) {
        this.stableId=stableId;
        this.name=name;
        this.rolePermissions=rolePermissions;
    }

    public abstract String getDisplayInfo();

    @Override
    public String toString() {
        return "Id: "+stableId+" Name: "+name+" Role permissions: "+rolePermissions;
    }

    public int getStableId() {
        return stableId;
    }
    public String getName() {
        return name;
    }
    public String getRolePermissions() {
        return rolePermissions;
    }

    @Override
    public int compareTo(User second) {
        return Integer.compare(this.stableId,second.stableId);
    }
}