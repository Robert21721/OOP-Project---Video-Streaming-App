package solution.Pages;

public final class SeeDetails implements Page {
    private static SeeDetails singletonInstance = null;
    private SeeDetails() { }
    public static SeeDetails getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new SeeDetails();
        }
        return  singletonInstance;
    }
}
