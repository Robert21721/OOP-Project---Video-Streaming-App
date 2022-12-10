package solution;

import input.files.CredentialsInput;

public final class Credentials {
    private String name;
    private String password;
    private String accountType;
    private String country;
    private int balance;

    public Credentials(final CredentialsInput c) {
        this.name = c.getName();
        this.password = c.getPassword();
        this.accountType = c.getAccountType();
        this.country = c.getCountry();
        this.balance = c.getBalance();
    }
}
