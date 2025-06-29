import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class Customer {
    int id;
    String name;
    int age;
    double balance;
    boolean isVIP;
    double loanInterestRate;
    LocalDate loanDueDate;

    public Customer(int id, String name, int age, double balance, double loanInterestRate, LocalDate loanDueDate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.balance = balance;
        this.loanInterestRate = loanInterestRate;
        this.loanDueDate = loanDueDate;
        this.isVIP = false;
    }
}

public class BankOperations {
    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<>();

      
        customers.add(new Customer(1, "Alice", 65, 12000.0, 5.5, LocalDate.now().plusDays(10)));
        customers.add(new Customer(2, "Bob", 45, 8000.0, 6.2, LocalDate.now().plusDays(40)));
        customers.add(new Customer(3, "Charlie", 70, 15000.0, 7.0, LocalDate.now().plusDays(5)));

       
        for (Customer c : customers) {
            if (c.age > 60) {
                c.loanInterestRate -= 1.0;
                System.out.println("Interest rate discount applied to: " + c.name);
            }
        }

       
        for (Customer c : customers) {
            if (c.balance > 10000.0) {
                c.isVIP = true;
                System.out.println(c.name + " has been promoted to VIP.");
            }
        }

        LocalDate today = LocalDate.now();
        for (Customer c : customers) {
            long daysUntilDue = ChronoUnit.DAYS.between(today, c.loanDueDate);
            if (daysUntilDue >= 0 && daysUntilDue <= 30) {
                System.out.println("Reminder: " + c.name + ", your loan is due in " + daysUntilDue + " days.");
            }
        }
    }
}
