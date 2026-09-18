package service;

import model.Loan;
import model.Reservation;
import model.items.Book;
import model.items.LibraryItem;
import model.people.Member;
import model.people.User;
import exception.LibraryException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;

public class CirculationService {

    private List<Loan> activeLoans;
    private Map<Book, Queue<Reservation>> reservationQueues;
    private int nextLoanId;
    private int nextReservationId;

    public CirculationService() {
        this.activeLoans = new ArrayList<>();
        this.reservationQueues = new HashMap<>();
        this.nextLoanId = 1;
        this.nextReservationId = 1;
    }

    public Loan borrowItem(User borrower, Book item, LocalDate loanDate, LocalDate dueDate) {

        item.borrow();

        Loan loan = new Loan(nextLoanId, borrower, dueDate, item, loanDate, null, false);
        nextLoanId = nextLoanId + 1;
        activeLoans.add(loan);

        return loan;
    }
    public void returnItem(Loan loan,LocalDate date) {
        Book book= (Book) loan.getItem();
        book.returnItem();
        loan.itemReturned(date);

        Queue<Reservation> queue = reservationQueues.get(book);
        if (queue == null || queue.isEmpty()) {
            return;
        }
        Reservation next = queue.poll();
        next.fulfill();
        int position = 1;
        for (Reservation reservation : queue) {
            reservation.setQueuePostion(position);
            position = position + 1;
        }
    }

}