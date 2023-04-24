package com.project.hrtsx.Model;

public enum TaskStatusEnum {

    START("Start"),
    IN_PROGRESS("In_Progress"),
    ON_HOLD("On_Hold"),
    CANCELLED("Cancelled"),
    COMPLETED("Completed");


    private final String taskStatus;


    TaskStatusEnum(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

}
