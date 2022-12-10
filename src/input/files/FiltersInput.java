package input.files;

public final class FiltersInput {
    private SortInput sort;
    private ContainsInput contains;

    public FiltersInput() { }

    public SortInput getSort() {
        return sort;
    }

    public void setSort(final SortInput sortInput) {
        this.sort = sortInput;
    }

    public ContainsInput getContains() {
        return contains;
    }

    public void setContains(final ContainsInput containsInput) {
        this.contains = containsInput;
    }
}
