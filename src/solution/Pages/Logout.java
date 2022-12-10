package solution.Pages;

public final class Logout implements Page {
    private static Logout singletonInstance = null;
    private Logout() { }
    public static Logout getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new Logout();
        }
        return  singletonInstance;
    }
}
