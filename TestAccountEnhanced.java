
import java.util.Arrays;
import java.util.List;

public class TestAccountEnhanced {

    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println(" ENHANCED ACCOUNT TEST (BOOLEAN RETURNS)");
        System.out.println("============================================================");

        System.out.println(">>> Test 1: Valid Account Creation");
        AccountEnhanced acc1 = new AccountEnhanced(1001, "John Doe", 25, 1000.0, "Savings");
        System.out.println(formatAccount(acc1));
        System.out.println();

        System.out.println(">>> Test 2: Invalid Age (under 18)");
        System.out.println("Creating account with age 16");
        AccountEnhanced acc2 = new AccountEnhanced(1002, "Young Kid", 16, 500.0, "Savings");
        if (acc2.getAge() == 18) {
            System.out.println("Age auto-corrected to: 18");
        }
        System.out.println(formatAccount(acc2));
        System.out.println();

        System.out.println(">>> Test 3: Invalid Account Type");
        System.out.println("Creating account with type \"Invalid\"");
        AccountEnhanced acc3 = new AccountEnhanced(1003, "Test User", 25, 500.0, "Invalid");
        if ("Savings".equalsIgnoreCase(acc3.getacctype())) {
            System.out.println("Account type defaulted to: Savings");
        }
        System.out.println(formatAccount(acc3));
        System.out.println();

        System.out.println(">>> Test 4: Minimum Balance Enforcement on Creation");
        System.out.println("Creating Savings account with ₹300 (below minimum)");
        AccountEnhanced acc4 = new AccountEnhanced(1004, "Bob Wilson", 25, 300.0, "Savings");
        if (acc4.getbal() == 500.0) {
            System.out.println("Balance auto-corrected to minimum: ₹500.0");
        }
        System.out.println(formatAccount(acc4));
        System.out.println();

        System.out.println(">>> Test 5: Withdrawal with Minimum Balance");
        AccountEnhanced acc5 = new AccountEnhanced(1005, "Alice Brown", 30, 1000.0, "Current");
        acc5.setPin(1111);
        System.out.println("Initial: " + formatAccount(acc5));
        if (acc5.withdraw(200.0, 1111)) {
            System.out.println("Withdrawing ₹200.0: SUCCESS");
            System.out.println("New balance: ₹" + acc5.getbal());
        }
        System.out.println("After withdrawal: " + formatAccount(acc5));
        double withdrawAmt = 900.0;
        double remainder = acc5.getbal() - withdrawAmt;
        if (!acc5.withdraw(withdrawAmt, 1111)) {
            System.out.println("Withdrawing ₹" + withdrawAmt + " (would leave ₹" + (int) remainder + "): FAILED (Minimum balance violation)");
            System.out.println("Current balance: ₹" + acc5.getbal());
        }
        System.out.println();

        System.out.println(">>> Test 6: Account Status Management");
        AccountEnhanced acc6 = new AccountEnhanced(1006, "Charlie Green", 35, 2000.0, "Savings");
        System.out.println("Initial: " + formatAccount(acc6));
        boolean closed = acc6.closeAccount();
        System.out.println("Closing account: " + (closed ? "SUCCESS" : "FAILED"));
        System.out.println("After close: " + formatAccount(acc6));
        if (!acc6.deposit(500.0)) {
            System.out.println("Depositing ₹500.0 to closed account: FAILED (Account inactive)");
        }
        boolean reopened = acc6.reopenAccount();
        System.out.println("Reopening account: " + (reopened ? "SUCCESS" : "FAILED"));
        System.out.println("After reopen: " + formatAccount(acc6));
        System.out.println();

        System.out.println(">>> Test 7: PIN Protection");
        AccountEnhanced acc7 = new AccountEnhanced(1007, "Diana Prince", 28, 1500.0, "Savings");
        boolean setPinResult = acc7.setPin(1234);
        System.out.println("Setting PIN 1234: " + (setPinResult ? "SUCCESS" : "FAILED"));
        if (acc7.withdraw(200.0, 1234)) {
            System.out.println("Withdrawing ₹200.0 with correct PIN (1234): SUCCESS");
            System.out.println("New balance: ₹" + acc7.getbal());
        }
        if (!acc7.withdraw(100.0, 9999)) {
            System.out.println("Withdrawing ₹100.0 with incorrect PIN (9999): FAILED (Incorrect PIN)");
        }
        if (!acc1.withdraw(100.0, 1234)) {
            System.out.println("Withdrawing ₹100.0 with PIN not set: FAILED (PIN not set)");
        }
        System.out.println();

        System.out.println(">>> Test 8: All Accounts Summary");
        List<AccountEnhanced> accounts = Arrays.asList(acc1, acc2, acc3, acc4, acc5, acc6, acc7);
        for (AccountEnhanced acc : accounts) {
            System.out.println(formatAccount(acc));
        }
        System.out.println("============================================================");
        System.out.println(" ENHANCED TEST COMPLETED!");
        System.out.println("============================================================");
    }

    private static String formatAccount(AccountEnhanced acc) {
        return "Account #" + acc.getaccno() + " | " + acc.getName() + " (" + acc.getAge() + " yrs) | "
                + acc.getacctype() + " | ₹" + acc.getbal() + " | " + acc.getStatus() + " | PIN: "
                + (acc.hasPin() ? "Yes" : "No");
    }
}
