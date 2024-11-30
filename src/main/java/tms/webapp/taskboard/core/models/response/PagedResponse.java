package tms.webapp.taskboard.core.models.response;

import java.util.List;

public class PagedResponse<T> {
    private final long total;
    private final List<T> items;
    public PagedResponse(final long total, final List<T> items) {
        this.total = total;
        this.items = items;
    }
    public long getTotal() {
        return total;
    }
    public List<T> getItems() {
        return items;
    }

    public static <T> PagedResponse<T> getInstance(final long total, final List<T> items)  {
        return new PagedResponse<T>(total, items);
    }
}
