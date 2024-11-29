package tms.webapp.taskboard.core.enums;

public enum ContentType {
    APPLICATION_JSON("application/json"),
    APPLICATION_XML("application/xml"),
    TEXT_HTML("text/html");

    private final String name;

    ContentType(String method) {
        this.name = method;
    }
    @Override
    public String toString() {
        return name;
    }
}
