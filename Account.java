public class Account {
    private static final double MIN_BALANCE_SAVINGS = 500.0;
    private static final double MIN_BALANCE_CURRENT = 1000.0;
    private static final int MIN_AGE = 18;
    private static final int MIN_PIN = 1000;
    private static final int MAX_PIN = 9999;
    private int accountNumber;
    private String name;
    private int age;
    private double balance;
    private String accountType;
    private String status;
    private Integer pin;
    public Account(int accountNumber, String name, int age, double initialBalance, String accountType)
            throws IllegalArgumentException {
        if (age < MIN_AGE) {
            throw new IllegalArgumentException("Invalid age: " + age + ". Age must be at least " + MIN_AGE + " years old.");
        }
        if (!"Savings".equalsIgnoreCase(accountType) && !"Current".equalsIgnoreCase(accountType)) {
            throw new IllegalArgumentException("Invalid account type: \"" + accountType + "\". Must be \"Savings\" or \"Current\".");
        }
        String normalizedType = "Current".equalsIgnoreCase(accountType) ? "Current" : "Savings";
        double requiredMinBalance = "Current".equalsIgnoreCase(normalizedType) ? MIN_BALANCE_CURRENT : MIN_BALANCE_SAVINGS;
        if (initialBalance < requiredMinBalance) {
            throw new IllegalArgumentException("Initial balance ₹" + initialBalance + " is below minimum required balance ₹" + requiredMinBalance + " for " + normalizedType + " account.");
        }
        this.accountNumber = accountNumber;
        this.name = name;
        this.age = age;
        this.balance = initialBalance;
        this.accountType = normalizedType;
        this.status = "Active";
        this.pin = null;
    }
    public void deposit(double amount) throws InvalidAmountException, InactiveAccountException {
        validateActive();
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive (greater than 0). Provided: ₹" + amount);
        }
        this.balance += amount;
    }

    public void withdraw(double amount, int pin)
            throws InvalidAmountException, InsufficientBalanceException, MinimumBalanceViolationException, InactiveAccountException, InvalidPinException {
        validateActive();
        if (!hasPin()) {
            throw new InvalidPinException("PIN not set for account #" + accountNumber);
        }
        if (!verifyPin(pin)) {
            throw new InvalidPinException("Incorrect PIN.");
        }
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive. Provided: ₹" + amount);
        }
        if (amount > this.balance) {
            throw new InsufficientBalanceException("Insufficient balance. Requested: ₹" + amount + ", Available: ₹" + this.balance);
        }
        double minBalance = getMinimumBalance();
        if (this.balance - amount < minBalance) {
            throw new MinimumBalanceViolationException("Withdrawal of ₹" + amount + " would leave ₹" + (this.balance - amount) + ", violating minimum balance requirement of ₹" + minBalance);
        }
        this.balance -= amount;
    }

    public void withdraw(double amount)
            throws InvalidAmountException, InsufficientBalanceException, MinimumBalanceViolationException, InactiveAccountException, InvalidPinException {
        validateActive();
        if (hasPin()) {
            throw new InvalidPinException("PIN is required for withdrawal.");
        }
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive. Provided: ₹" + amount);
        }
        if (amount > this.balance) {
            throw new InsufficientBalanceException("Insufficient balance. Requested: ₹" + amount + ", Available: ₹" + this.balance);
        }
        double minBalance = getMinimumBalance();
        if (this.balance - amount < minBalance) {
            throw new MinimumBalanceViolationException("Withdrawal of ₹" + amount + " would leave ₹" + (this.balance - amount) + ", violating minimum balance requirement of ₹" + minBalance);
        }
        this.balance -= amount;
    }
    public void closeAccount() throws IllegalStateException {
        if ("Inactive".equalsIgnoreCase(this.status)) {
            throw new IllegalStateException("Account #" + accountNumber + " is already closed.");
        }
        this.status = "Inactive";
    }

    public void reopenAccount() throws IllegalStateException {
        if ("Active".equalsIgnoreCase(this.status)) {
            throw new IllegalStateException("Account #" + accountNumber + " is already active.");
        }
        this.status = "Active";
    }
    public void setPin(int pin) throws IllegalArgumentException {
        if (pin < MIN_PIN || pin > MAX_PIN) {
            throw new IllegalArgumentException("PIN must be a 4-digit number (1000-9999). Provided: " + pin);
        }
        this.pin = pin;
    }

    public boolean verifyPin(int pin) {
        if (this.pin == null) {
            return false;
        }
        return this.pin == pin;
    }

    public boolean hasPin() {
        return this.pin != null;
    }

    private double getMinimumBalance() {
        if ("Current".equalsIgnoreCase(this.accountType)) {
            return MIN_BALANCE_CURRENT;
        }
        return MIN_BALANCE_SAVINGS;
    }

    private void validateActive() throws InactiveAccountException {
        if (!"Active".equalsIgnoreCase(this.status)) {
            throw new InactiveAccountException("Operation failed: Account #" + accountNumber + " is inactive.");
        }
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getaccno() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getBalance() {
        return balance;
    }

    public double getbal() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getacctype() {
        return accountType;
    }

    public String getStatus() {
        return status;
    }

    public Integer getPin() {
        return pin;
    }

    public void setName(String name) {
        this.name = name;
    }
}
