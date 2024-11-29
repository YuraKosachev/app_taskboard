package tms.webapp.taskboard.core.interfaces.units.base;

public interface FindUnit<P,R> {
    R find(P predicate);
}
