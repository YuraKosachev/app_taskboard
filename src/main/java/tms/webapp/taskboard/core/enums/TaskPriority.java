package tms.webapp.taskboard.core.enums;

public enum TaskPriority {
    HIGH("High",1),
    MEDIUM("Medium",2),
    LOW("Low",3);

    private final String priority;
    private final int value;

    TaskPriority(String priority, int value) {
        this.priority = priority;
        this.value = value;
    }
    public String getPriority() {
        return priority;
    }
    public int getValue() {
        return value;
    }
    public static TaskPriority getPriorityByValue(int value) {
        for (TaskPriority priority : TaskPriority.values()) {
            if (priority.getValue() == value) {
                return priority;
            }
        }
        throw new EnumConstantNotPresentException(TaskPriority.class,"%d not found".formatted(value));
    }
    public static TaskPriority getTaskPriority(String priority) {
        for (TaskPriority t : TaskPriority.values()) {
            if (t.getPriority().equals(priority)) {
                return t;
            }
        }
        throw new EnumConstantNotPresentException(TaskPriority.class,"%s not found".formatted(priority));
    }

}
