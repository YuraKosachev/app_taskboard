package tms.webapp.taskboard.core.interfaces.units.base;

import java.util.List;

public interface SelectUnit<M,R> {
    List<R> getAll(M predicate);
}
