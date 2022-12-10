package solution.Pages;

public final class Register implements Page {
    private static Register singletonInstance = null;
    private Register() { }
    public static Register getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new Register();
        }
        return  singletonInstance;
    }
}
