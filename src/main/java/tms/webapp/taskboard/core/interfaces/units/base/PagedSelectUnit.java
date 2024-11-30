package tms.webapp.taskboard.core.interfaces.units.base;

import tms.webapp.taskboard.core.models.response.PagedResponse;

public interface PagedSelectUnit<M,R> {
    PagedResponse<R> getPagedByPredicate(M predicate);
}
