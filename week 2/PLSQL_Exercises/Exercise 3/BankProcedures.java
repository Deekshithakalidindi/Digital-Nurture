import java.util.*;

class Account {
    int accountId;
    double balance;
    String type; // e.g., "savings"

    Account(int accountId, double balance, String type) {
        this.accountId = accountId;
        this.balance = balance;
        this.type = type;
    }
}

class Employee {
    int employeeId;
    String name;
    String department;
    double salary;

    Employee(int employeeId, String name, String department, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
}

public class BankProcedures {

    static List<Account> accounts = new ArrayList<>();
    static List<Employee> employees = new ArrayList<>();

    
    public static void processMonthlyInterest() {
        for (Account acc : accounts) {
            if (acc.type.equalsIgnoreCase("savings")) {
                acc.balance += acc.balance * 0.01; // 1% interest
                System.out.println("Interest applied to Account ID: " + acc.accountId);
            }
        }
    }


    public static void updateEmployeeBonus(String department, double bonusPercent) {
        for (Employee emp : employees) {
            if (emp.department.equalsIgnoreCase(department)) {
                emp.salary += emp.salary * (bonusPercent / 100);
                System.out.println("Bonus updated for " + emp.name);
            }
        }
    }

  
    public static void transferFunds(int fromAccountId, int toAccountId, double amount) {
        Account fromAcc = null, toAcc = null;

        for (Account acc : accounts) {
            if (acc.accountId == fromAccountId) fromAcc = acc;
            if (acc.accountId == toAccountId) toAcc = acc;
        }

        if (fromAcc == null || toAcc == null) {
            System.out.println("Invalid account IDs.");
            return;
        }

        if (fromAcc.balance >= amount) {
            fromAcc.balance -= amount;
            toAcc.balance += amount;
            System.out.println("Transferred $" + amount + " from Account " + fromAccountId + " to " + toAccountId);
        } else {
            System.out.println("Insufficient balance in source account.");
        }
    }

    public static void main(String[] args) {
     
        accounts.add(new Account(101, 5000.0, "savings"));
        accounts.add(new Account(102, 3000.0, "checking"));
        accounts.add(new Account(103, 7000.0, "savings"));

        employees.add(new Employee(1, "Alice", "IT", 60000));
        employees.add(new Employee(2, "Bob", "HR", 50000));
        employees.add(new Employee(3, "Charlie", "IT", 65000));

     
        System.out.println("----Monthly Interest----");
        processMonthlyInterest();

        System.out.println("\n----Bonus Update (IT Department)----");
        updateEmployeeBonus("IT", 10.0); // 10% bonus

        System.out.println("\n----Fund Transfer----");
        transferFunds(101, 102, 1000.0);
    }
}
