package model;


import model.items.LibraryItem;
import model.people.Member;


public class Reservation implements Comparable<Reservation>{
    private int reservationId;
    private Member member;
    private LibraryItem item;
    private int queuePostion;
    private String state;

    public Reservation(int reservationId,Member member,LibraryItem item,int queuePosition) {
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
    public Member getMember() {
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
