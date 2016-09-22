package erica.todolist2;

public class ToDo {
    private long id;
    private String taskName;
    private int status;

    public ToDo(String taskName, int status) {
        this.taskName = taskName;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
