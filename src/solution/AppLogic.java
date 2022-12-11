package solution;

import solution.Pages.HomePageNeautentificat;
import solution.Pages.Movies;
import solution.Pages.Page;

import java.util.ArrayList;

public final class AppLogic {
    private Page currentPage;
    private User currentUser;
    private ArrayList<Movies> currentMovies;

    public AppLogic() {
        this.currentPage = HomePageNeautentificat.getInstance();
        this.currentMovies = new ArrayList<>();
        this.currentUser = null;
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

    public ArrayList<Movies> getCurrentMovies() {
        return currentMovies;
    }

    public void setCurrentMovies(ArrayList<Movies> currentMovies) {
        this.currentMovies = currentMovies;
    }
}
