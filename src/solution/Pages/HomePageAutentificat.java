package solution.Pages;

public final class HomePageAutentificat implements Page {
    private static HomePageAutentificat singletonInstance = null;
    private HomePageAutentificat() { }
    public static HomePageAutentificat getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new HomePageAutentificat();
        }
        return singletonInstance;
    }
}
