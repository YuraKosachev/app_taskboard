package tms.webapp.taskboard.core.models.migration;

public class MigrationPredicate {
    private final int[] ids;
    private final String[] names;

    public MigrationPredicate(final int[] ids, final String[] names) {
        this.ids = ids;
        this.names = names;
    }
    public int[] getIds() {
        return ids;
    }
    public String[] getNames() {
        return names;
    }

}
