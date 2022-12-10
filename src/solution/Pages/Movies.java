package solution.Pages;

public final class Movies implements Page {
    private static Movies singletonInstance = null;
    private Movies() { }
    public static Movies getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new Movies();
        }
        return  singletonInstance;
    }
}
