package solution.Pages;

public final class Upgrades implements Page {
    private static Upgrades singletonInstance = null;
    private Upgrades() { }
    public static Upgrades getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new Upgrades();
        }
        return  singletonInstance;
    }
}
