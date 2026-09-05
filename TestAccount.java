import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class TestAccount {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Account> accounts = new ArrayList<>();
        System.out.println(" GLOBAL DIGITAL BANK - INTERACTIVE SYSTEM");
        while (true) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Create New Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Display Single Account Info");
            System.out.println("5. Display All Accounts");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");
            String input = sc.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 6.");
                continue;
            }
            switch (choice) {
                case 1:
                    createAccount(sc, accounts);
                    break;
                case 2:
                    depositMoney(sc, accounts);
                    break;
                case 3:
                    withdrawMoney(sc, accounts);
                    break;
                case 4:
                    displaySingleAccount(sc, accounts);
                    break;
                case 5:
                    displayAllAccounts(accounts);
                    break;
                case 6:
                    System.out.println("\nThank you for using Global Digital Bank!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please select 1-6.");
            }
        }
    }

    public static void createAccount(Scanner sc, List<Account> accounts) {
        System.out.println("\n--- Create Account ---");
        try {
            System.out.print("Enter Account Number: ");
            int accno = Integer.parseInt(sc.nextLine().trim());
            if (findAccount(accounts, accno) != null) {
                System.out.println("Error: Account number " + accno + " already exists!");
                return;
            }
            System.out.print("Enter Name: ");
            String name = sc.nextLine().trim();
            System.out.print("Enter Age: ");
            int age = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Enter Initial Balance: ");
            double inbal = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Enter Account Type (Savings/Current): ");
            String acctype = sc.nextLine().trim();
            Account acc = new Account(accno, name, age, inbal, acctype);
            accounts.add(acc);
            System.out.println("Account created successfully!");
            printAccountInfo(acc);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format entered. Account creation failed.");
        }
    }
    public static void depositMoney(Scanner sc, List<Account> accounts) {
        System.out.println("\n--- Deposit Money ---");
        if (accounts.isEmpty()) {
            System.out.println("No accounts found! Please create an account first.");
            return;
        }
        try {
            System.out.print("Enter Account Number: ");
            int accno = Integer.parseInt(sc.nextLine().trim());
            Account acc = findAccount(accounts, accno);
            if (acc == null) {
                System.out.println("Account #" + accno + " not found!");
                return;
            }
            System.out.print("Enter amount to deposit: ");
            double amount = Double.parseDouble(sc.nextLine().trim());
            if (acc.deposit(amount)) {
                System.out.println("Depositing INR " + amount + ": SUCCESS");
                System.out.println("New balance: INR " + acc.getbal());
            } else {
                System.out.println("Depositing INR " + amount + ": FAILED (Invalid amount)");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format entered.");
        }
    }
    public static void withdrawMoney(Scanner sc, List<Account> accounts) {
        System.out.println("\n--- Withdraw Money ---");
        if (accounts.isEmpty()) {
            System.out.println("No accounts found! Please create an account first.");
            return;
        }
        try {
            System.out.print("Enter Account Number: ");
            int accno = Integer.parseInt(sc.nextLine().trim());
            Account acc = findAccount(accounts, accno);
            if (acc == null) {
                System.out.println("Account #" + accno + " not found!");
                return;
            }
            System.out.print("Enter amount to withdraw: ");
            double amount = Double.parseDouble(sc.nextLine().trim());
            if (acc.withdraw(amount)) {
                System.out.println("Withdrawing INR " + amount + ": SUCCESS");
                System.out.println("New balance: INR " + acc.getbal());
            } else {
                System.out.println("Withdrawing INR " + amount + ": FAILED (Insufficient balance or invalid amount)");
                System.out.println("Current balance: INR " + acc.getbal());
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format entered.");
        }
    }
    public static void displaySingleAccount(Scanner sc, List<Account> accounts) {
        System.out.println("\n--- Account Info ---");
        if (accounts.isEmpty()) {
            System.out.println("No accounts found!");
            return;
        }
        try {
            System.out.print("Enter Account Number: ");
            int accno = Integer.parseInt(sc.nextLine().trim());
            Account acc = findAccount(accounts, accno);
            if (acc == null) {
                System.out.println("Account #" + accno + " not found!");
            } else {
                printAccountInfo(acc);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid account number.");
        }
    }
    public static void displayAllAccounts(List<Account> accounts) {
        System.out.println("\n--- All Accounts ---");
        if (accounts.isEmpty()) {
            System.out.println("No accounts found!");
            return;
        }
        for (Account acc : accounts) {
            printAccountInfo(acc);
        }
    }
    private static Account findAccount(List<Account> accounts, int accno) {
        for (Account acc : accounts) {
            if (acc.getaccno() == accno) {
                return acc;
            }
        }
        return null;
    }
    private static void printAccountInfo(Account acc) {
        System.out.println("Account #" + acc.getaccno() + " | " + acc.getName() + " (" + acc.getAge() + " yrs) | " + acc.getacctype() + " | INR " + acc.getbal() + " | " + acc.getStatus());
    }
}
