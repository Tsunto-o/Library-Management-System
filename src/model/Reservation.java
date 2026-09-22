package model;


import model.items.LibraryItem;
import model.user.User;


public class Reservation implements Comparable<Reservation>{
    private int reservationId;
    private User member;
    private LibraryItem item;
    private int queuePostion;
    private String state;

    public Reservation(int reservationId,User member,LibraryItem item,int queuePosition) {
        this.reservationId=reservationId;
        this.member=member;
        this.item=item;
        this.queuePostion=queuePosition;
        this.state="PENDING";

    }
    public void setQueuePostion(int position) {
        this.queuePostion=position;
    }
    public void fulfill() {
        this.state="FULFILLED";
    }
    public void cancel() {
        this.state="CANCELLED";
    }
    public int getReservationId() {
        return reservationId;
    }
    public User getMember() {
        return member;
    }
    public LibraryItem getItem () {
        return item;
    }
    public int getQueuePosition() {
        return queuePostion;
    }
    public String getState() {
        return state;
    }
    @Override
    public int compareTo(Reservation second) {
             return Integer.compare(this.queuePostion,second.queuePostion);


    }
}
