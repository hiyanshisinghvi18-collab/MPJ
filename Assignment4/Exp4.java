import java.io.*;
import java.util.*;

// Custom Exceptions
class BalanceTooLowException extends Exception {
    public BalanceTooLowException(String msg) {
        super(msg);
    }
}

class CustomerIdException extends Exception {
    public CustomerIdException(String msg) {
        super(msg);
    }
}

class NotEnoughBalanceException extends Exception {
    public NotEnoughBalanceException(String msg) {
        super(msg);
    }
}

class AmountException extends Exception {
    public AmountException(String msg) {
        super(msg);
    }
}

// Main Banking Class
class BankApp {

    private int customerId;
    private String customerName;
    private double balance;

    private static Scanner input = new Scanner(System.in);

    // Method to open account
    public void openAccount() {
        try {
            System.out.print("Enter ID (1-20): ");
            customerId = input.nextInt();

            validateCustomerId(customerId);

            System.out.print("Enter Name: ");
            customerName = input.next();

            System.out.print("Enter Initial Deposit: ");
            double initialDeposit = input.nextDouble();

            validateAmount(initialDeposit);
            checkMinimumBalance(initialDeposit);

            balance = initialDeposit;

            saveToFile();

            System.out.println("Account opened successfully!");

        } catch (Exception e) {
            System.out.println("Operation Failed: " + e.getMessage());
        }
    }

    // Withdraw money
    public void withdrawMoney() {
        try {
            System.out.print("Enter amount to withdraw: ");
            double amt = input.nextDouble();

            validateAmount(amt);

            if (amt > balance) {
                throw new NotEnoughBalanceException("Insufficient funds!");
            }

            balance -= amt;

            System.out.println("Withdrawal done!");
            System.out.println("Current Balance: " + balance);

        } catch (Exception e) {
            System.out.println("Operation Failed: " + e.getMessage());
        }
    }

    // Display stored data
    public void showAccounts() {
        try (BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"))) {

            System.out.println("\n--- Account Records ---");
            String record;

            while ((record = reader.readLine()) != null) {
                System.out.println(record);
            }

        } catch (FileNotFoundException e) {
            System.out.println("No records found.");
        } catch (IOException e) {
            System.out.println("File error occurred.");
        }
    }

    // Helper Methods
    private void validateCustomerId(int id) throws CustomerIdException {
        if (id < 1 || id > 20) {
            throw new CustomerIdException("ID must be between 1 and 20.");
        }
    }

    private void validateAmount(double amt) throws AmountException {
        if (amt <= 0) {
            throw new AmountException("Amount should be greater than zero.");
        }
    }

    private void checkMinimumBalance(double amt) throws BalanceTooLowException {
        if (amt < 1000) {
            throw new BalanceTooLowException("Minimum balance required is 1000.");
        }
    }

    private void saveToFile() throws IOException {
        FileWriter writer = new FileWriter("accounts.txt", true);
        writer.write(customerId + " " + customerName + " " + balance + "\n");
        writer.close();
    }

    // Main Menu
    public static void main(String[] args) {

        BankApp app = new BankApp();
        int option;

        do {
            System.out.println("\n==== BANK MENU ====");
            System.out.println("1. Open Account");
            System.out.println("2. Withdraw Money");
            System.out.println("3. View Records");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            option = input.nextInt();

            switch (option) {
                case 1:
                    app.openAccount();
                    break;

                case 2:
                    app.withdrawMoney();
                    break;

                case 3:
                    app.showAccounts();
                    break;

                case 4:
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid option!");
            }

        } while (option != 4);
    }
}