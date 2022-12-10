package solution;

import solution.Pages.Page;

public final class AppLogic {
    private Page currentPage;
    private User currentUser;

    public AppLogic() { }

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
}
