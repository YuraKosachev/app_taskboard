package tms.webapp.taskboard.core.models.datetime;

import java.time.LocalDate;

public class DateRange {
    private final LocalDate start;
    private final LocalDate end;
    public DateRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }
    public LocalDate getStart() {
        return start;
    }
    public LocalDate getEnd() {
        return end;
    }

    public static DateRange getInstance(LocalDate start, LocalDate end) {
        return new DateRange(start, end);
    }
}
