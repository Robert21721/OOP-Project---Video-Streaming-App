package solution.Pages;

public final class HomePageNeautentificat implements Page {
    private static HomePageNeautentificat singletonInstance = null;
    private HomePageNeautentificat() { }
    public static HomePageNeautentificat getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new HomePageNeautentificat();
        }
        return  singletonInstance;
    }
}
