package solution;

import input.files.UsersInput;

public final class User {
    private Credentials credentials;
    public User(final UsersInput u) {
        this.credentials = new Credentials(u.getCredentials());
    }
}
