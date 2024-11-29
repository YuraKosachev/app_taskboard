package tms.webapp.taskboard.core.models.language;

public class AlpinaLanguage {
    private final int id;
    private final String key;
    private final String value;
    public AlpinaLanguage(int id, String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }
    public int getId() {
        return id;
    }
    public String getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }

    public static AlpinaLanguage getAlpinaLanguage(int id, String key, String value) {
        return new AlpinaLanguage(id, key, value);
    }
}
