package solution;

import solution.pages.HomePageNeautentificat;
import solution.pages.Page;
import java.util.ArrayList;

public final class AppLogic {
    private Page currentPage;
    private User currentUser;
    private ArrayList<Movie> currentMovies;

    public AppLogic() {
        this.currentPage = HomePageNeautentificat.getInstance();
        this.currentMovies = new ArrayList<>();
        this.currentUser = null;
    }

    public AppLogic(final AppLogic a) {
        this.currentPage = a.getCurrentPage();
        this.currentUser = new User(a.getCurrentUser());
        this.currentMovies = new ArrayList<>();

        for (Movie m : a.getCurrentMovies()) {
            this.currentMovies.add(new Movie(m));
        }
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(final Page currentPage) {
        this.currentPage = currentPage;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(final User currentUser) {
        this.currentUser = currentUser;
    }

    public ArrayList<Movie> getCurrentMovies() {
        return currentMovies;
    }

    public void setCurrentMovies(final ArrayList<Movie> currentMovies) {
        this.currentMovies = currentMovies;
    }
}
