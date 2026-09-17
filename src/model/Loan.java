package model;

import model.items.LibraryItem;

import java.time.LocalDate;

public class Loan implements Comparable<Loan> {
    private int loanId;
    private User borrower;
    private LocalDate dueDate;
    private LibraryItem item;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private boolean returned;


    public Loan(int loanId,User borrower,LocalDate dueDate, LibraryItem item, LocalDate loanDate, LocalDate returnDate, boolean returned) {
        this.loanId=loanId;
        this.borrower=borrower;
        this.dueDate=dueDate;
        this.item=item;
        this.loanDate=loanDate;
        this.returnDate=returnDate;
        this.returned=returned;

    }

    public int getLoanId () {
        return loanId;
    }
    public LocalDate getReturnDate() {
        return returnDate;
    }

    public User getBorrower() {
        return borrower;
    }
    public LibraryItem getItem() {
        return item;
    }
    public LocalDate getLoanDate() {
        return loanDate;
    }
    public boolean getReturned() {
        return returned;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public int compareTo(Loan second) {
        return Integer.compare(this.loanId,second.loanId);
    }
}