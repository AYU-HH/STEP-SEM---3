public class TestAccountException {

    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println(" ACTIVITY 5: ACCOUNT TEST (WITH EXCEPTIONS)");
        System.out.println("============================================================");

        // Test 1: Valid Account Creation
        System.out.println(">>> Test 1: Valid Account Creation");
        try {
            Account acc1 = new Account(1001, "John Doe", 25, 1000.0, "Savings");
            System.out.println("Account created successfully: " + formatAccount(acc1));
        } catch (IllegalArgumentException e) {
            System.out.println("Creation FAILED: " + e.getMessage());
        }
        System.out.println();

        // Test 2: Invalid Age (< 18)
        System.out.println(">>> Test 2: Invalid Age (< 18)");
        try {
            Account acc2 = new Account(1002, "Young Kid", 16, 500.0, "Savings");
            System.out.println("Account created: " + formatAccount(acc2));
        } catch (IllegalArgumentException e) {
            System.out.println("Creation FAILED (Caught IllegalArgumentException): " + e.getMessage());
        }
        System.out.println();

        // Test 3: Invalid Account Type
        System.out.println(">>> Test 3: Invalid Account Type");
        try {
            Account acc3 = new Account(1003, "Test User", 25, 500.0, "Invalid");
            System.out.println("Account created: " + formatAccount(acc3));
        } catch (IllegalArgumentException e) {
            System.out.println("Creation FAILED (Caught IllegalArgumentException): " + e.getMessage());
        }
        System.out.println();

        // Test 4: Initial Balance Below Minimum
        System.out.println(">>> Test 4: Initial Balance Below Minimum");
        try {
            Account acc4 = new Account(1004, "Bob Wilson", 25, 300.0, "Savings");
            System.out.println("Account created: " + formatAccount(acc4));
        } catch (IllegalArgumentException e) {
            System.out.println("Creation FAILED (Caught IllegalArgumentException): " + e.getMessage());
        }
        System.out.println();

        // Test 5: Exception handling during deposit and withdrawal
        System.out.println(">>> Test 5: Operations with Exceptions");
        try {
            Account acc5 = new Account(1005, "Alice Brown", 30, 2000.0, "Savings");
            acc5.setPin(1234);
            System.out.println("Initial: " + formatAccount(acc5));

            // Successful withdrawal
            acc5.withdraw(500.0, 1234);
            System.out.println("Withdrawal ₹500.0 SUCCESS. New balance: ₹" + acc5.getBalance());

            // Invalid PIN
            try {
                acc5.withdraw(200.0, 9999);
            } catch (InvalidPinException e) {
                System.out.println("Withdrawal FAILED (Caught InvalidPinException): " + e.getMessage());
            }

            // Minimum balance violation
            try {
                acc5.withdraw(1200.0, 1234); // Leaves 300 (< 500 min balance)
            } catch (MinimumBalanceViolationException e) {
                System.out.println("Withdrawal FAILED (Caught MinimumBalanceViolationException): " + e.getMessage());
            }

            // Inactive account exception
            acc5.closeAccount();
            try {
                acc5.deposit(500.0);
            } catch (InactiveAccountException e) {
                System.out.println("Deposit FAILED (Caught InactiveAccountException): " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        }
        System.out.println();

        System.out.println("============================================================");
        System.out.println(" ACTIVITY 5 TEST COMPLETED!");
        System.out.println("============================================================");
    }

    private static String formatAccount(Account acc) {
        return "Account #" + acc.getAccountNumber() + " | " + acc.getName() + " (" + acc.getAge() + " yrs) | "
                + acc.getAccountType() + " | ₹" + acc.getBalance() + " | " + acc.getStatus() + " | PIN: "
                + (acc.hasPin() ? "Yes" : "No");
    }
}
