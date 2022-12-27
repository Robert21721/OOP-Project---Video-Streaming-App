package solution;

public class Notification {
    private String movieName;
    private String message;

    public Notification(String movieName, String message) {
        this.movieName = movieName;
        this.message = message;
    }

    public Notification(Notification n) {
        this.movieName = n.getMovieName();
        this.message = n.getMessage();
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
