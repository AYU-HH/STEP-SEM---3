
public class AccountEnhanced {

    private int accno;
    private String name;
    private int age;
    private double balance;
    private String acctype;
    private String status;
    private Integer pin;

    public AccountEnhanced(int accountNumber, String name, int age, double initialBalance, String accountType) {
        this.accno = accountNumber;
        this.name = name;
        if (age < 18) {
            this.age = 18;
        } else {
            this.age = age;
        }
        if ("Savings".equalsIgnoreCase(accountType)) {
            this.acctype = "Savings";
        } else if ("Current".equalsIgnoreCase(accountType)) {
            this.acctype = "Current";
        } else {
            this.acctype = "Savings";
        }
        double minBalance = "Current".equalsIgnoreCase(this.acctype) ? 1000.0 : 500.0;
        if (initialBalance < minBalance) {
            this.balance = minBalance;
        } else {
            this.balance = initialBalance;
        }

        this.status = "Active";
        this.pin = null;
    }

    public boolean deposit(double amount) {
        if (!"Active".equalsIgnoreCase(this.status)) {
            return false;
        }
        if (amount <= 0) {
            return false;
        }
        this.balance += amount;
        return true;
    }

    public boolean withdraw(double amount) {
        if (!"Active".equalsIgnoreCase(this.status)) {
            return false;
        }
        if (hasPin()) {
            return false;
        }
        if (amount <= 0 || (this.balance - amount < 0)) {
            return false;
        }
        this.balance -= amount;
        return true;
    }

    public boolean withdraw(double amount, int pin) {
        if (!"Active".equalsIgnoreCase(this.status)) {
            return false;
        }
        if (!verifyPin(pin)) {
            return false;
        }
        if (amount <= 0 || (this.balance - amount < 0)) {
            return false;
        }
        this.balance -= amount;
        return true;
    }

    public boolean closeAccount() {
        if ("Inactive".equalsIgnoreCase(this.status)) {
            return false;
        }
        this.status = "Inactive";
        return true;
    }

    public boolean reopenAccount() {
        if ("Active".equalsIgnoreCase(this.status)) {
            return false;
        }
        this.status = "Active";
        return true;
    }

    public boolean setPin(int pin) {
        if (pin >= 1000 && pin <= 9999) {
            this.pin = pin;
            return true;
        }
        return false;
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

    public int getaccno() {
        return accno;
    }

    public int getAccountNumber() {
        return accno;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getbal() {
        return balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getacctype() {
        return acctype;
    }

    public String getAccountType() {
        return acctype;
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

    public void setAge(int age) {
        if (age >= 18) {
            this.age = age;
        }
    }
}
