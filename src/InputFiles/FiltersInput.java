package InputFiles;

public class FiltersInput {
    private SortInput sort;
    private ContainsInput contains;

    public FiltersInput() { }

    public SortInput getSort() {
        return sort;
    }

    public void setSort(SortInput sortInput) {
        this.sort = sortInput;
    }

    public ContainsInput getContains() {
        return contains;
    }

    public void setContains(ContainsInput containsInput) {
        this.contains = containsInput;
    }
}
