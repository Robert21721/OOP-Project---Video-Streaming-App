package solution;

import input.files.CredentialsInput;

public final class Credentials {
    private String name;
    private String password;
    private String accountType;
    private String country;
    private String balance;

    public Credentials() { }
    public Credentials(final CredentialsInput c) {
        this.name = c.getName();
        this.password = c.getPassword();
        this.accountType = c.getAccountType();
        this.country = c.getCountry();
        this.balance = c.getBalance();
    }

    public Credentials(final Credentials c) {
        this.name = c.getName();
        this.password = c.getPassword();
        this.accountType = c.getAccountType();
        this.country = c.getCountry();
        this.balance = c.getBalance();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(final String balance) {
        this.balance = balance;
    }
}
