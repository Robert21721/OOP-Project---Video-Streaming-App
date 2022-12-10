package solution.Pages;

public final class Login implements Page {
    private static Login singletonInstance = null;
    private Login() { }
    public static Login getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new Login();
        }
        return  singletonInstance;
    }
}
