package service;

import model.Loan;
import model.Reservation;
import model.items.Book;
import model.user.User;
import exception.LibraryException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CirculationService {

    private List<Loan> activeLoans;
    private Map<Book, List<Reservation>> reservationQueues;
    private int nextLoanId;
    private int nextReservationId;
    private final Loan.DueDatePolicy dueDatePolicy;

    public CirculationService() {
        this.activeLoans = new ArrayList<>();
        this.reservationQueues = new HashMap<>();
        this.nextLoanId = 1;
        this.nextReservationId = 1;
        this.dueDatePolicy = new Loan.DueDatePolicy(14);
    }

    public Loan borrowItem(User borrower, Book item, LocalDate loanDate) {
        item.borrow();

        LocalDate dueDate = dueDatePolicy.computeDueDate(loanDate);
        Loan loan = new Loan(nextLoanId, borrower, dueDate, item, loanDate, null, false);
        nextLoanId = nextLoanId + 1;
        activeLoans.add(loan);

        return loan;
    }

    public void returnItem(Loan loan, LocalDate date) {
        Book book = (Book) loan.getItem();
        book.returnItem();
        loan.itemReturned(date);

        List<Reservation> queue = reservationQueues.get(book);
        if (queue == null || queue.isEmpty()) {
            return;
        }
        Reservation next = queue.removeFirst();
        next.fulfill();
        borrowItem(next.getMember(), book, date);
        int position = 1;
        for (Reservation reservation : queue) {
            reservation.setQueuePostion(position);
            position = position + 1;
        }
    }

    public Reservation reserveItem(Book item, User member) {
        if (item.isAvailable()) {
            throw new LibraryException("You can't reserve an item already available");
        }

        if (!reservationQueues.containsKey(item)) {
            reservationQueues.put(item, new ArrayList<>());
        }

        List<Reservation> itemQueue = reservationQueues.get(item);

        int position = itemQueue.size() + 1;
        Reservation reservation = new Reservation(nextReservationId, member, item, position);
        nextReservationId = nextReservationId + 1;

        itemQueue.add(reservation);

        return reservation;
    }

    public List<Loan> getActiveLoans() {
        return Collections.unmodifiableList(activeLoans);
    }

    public Map<Book, List<Reservation>> getReservationQueues() {
        return Collections.unmodifiableMap(reservationQueues);
    }

}