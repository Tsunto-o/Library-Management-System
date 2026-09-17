package ordering;

import model.Loan;

import java.util.Comparator;

public class DueDateComparator implements Comparator<Loan> {
    @Override
    public int compare(Loan first, Loan second) {
        int result = first.getDueDate().compareTo(second.getDueDate());
        if (result == 0) {
            return first.compareTo(second);
        }
        return result;
    }

}
