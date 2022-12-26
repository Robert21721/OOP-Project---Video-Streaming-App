package solution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import solution.Commands.Editor;
import solution.pages.HomePageNeautentificat;
import solution.pages.Page;
import java.util.ArrayList;

@JsonIgnoreProperties(value = {
        "editor"
})

public final class AppLogic {
    private Page currentPage;
    private User currentUser;
    private ArrayList<Movie> currentMovies;

    private Editor editor;

    public AppLogic() {
        this.currentPage = HomePageNeautentificat.getInstance();
        this.currentMovies = new ArrayList<>();
        this.currentUser = null;
        this.editor = new Editor();
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

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }
}
