package tms.webapp.taskboard.core.enums;

public enum TaskStatus {
    TODO("ToDo",1),
    INPROGRESS("InProgress",2),
    DONE("Done",3),
    DELETED("Deleted",4);

    private final String status;
    private final int value;

    TaskStatus(String status, int value) {
        this.status = status;
        this.value = value;
    }
    public String getStatus() {
        return status;
    }
    public int getValue() {
        return value;
    }

    public static TaskStatus getTaskStatus(int value) {
        for (TaskStatus taskStatus : TaskStatus.values()) {
            if (taskStatus.getValue() == value) {
                return taskStatus;
            }
        }
        throw new EnumConstantNotPresentException(TaskStatus.class,"%d not found".formatted(value));
    }
    public static TaskStatus getTaskStatus(String status) {
        for (TaskStatus taskStatus : TaskStatus.values()) {
            if (taskStatus.getStatus().equals(status)) {
                return taskStatus;
            }
        }
        throw new EnumConstantNotPresentException(TaskStatus.class,"%s not found".formatted(status));
    }


}
