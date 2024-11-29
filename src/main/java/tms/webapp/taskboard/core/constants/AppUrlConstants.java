package tms.webapp.taskboard.core.constants;

public final class AppUrlConstants {
    private static final String JSP_FORMAT = "%s.jsp";
    public static final String LOGIN_URL = "/login";
    public static final String TODOLIST_URL = "/api/todolist";
    public static final String TASK_URL = "/api/task";
    public static final String TASK_STATUS_URL = "/api/task/status";
    public static final String TASK_PRIORITY_URL = "/api/task/priority";
    public static final String LANGUAGE_URL = "/api/languages";
    public static final String INDEX_URL = "/";
    public static final String INDEX_URL_JSP = JSP_FORMAT.formatted("/index");
    public static final String LOGOUT_URL = "/logout";
    public static final String LOGIN_URL_JSP = JSP_FORMAT.formatted(LOGIN_URL);
    public static final String REGISTRATION_URL = "/registration";
    public static final String REGISTRATION_URL_JSP = JSP_FORMAT.formatted(REGISTRATION_URL);
    public static final String ASSETS_STATIC_URL = "/assets";

    public static final String[] UNAUTHORIZED_URLS = {
            LOGIN_URL,
            REGISTRATION_URL,
            ASSETS_STATIC_URL,
            LANGUAGE_URL
    };
}