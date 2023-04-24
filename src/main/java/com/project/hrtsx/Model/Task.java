package com.project.hrtsx.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="task_id")
    private Long id;

    @Column(name = "taskName")
    private String taskName;

    @Column(name = "taskFinishDate")
    private String taskFinishDate;

    @Column(name = "taskExplanation")
    private String taskExplanation;

    @Enumerated(EnumType.STRING)
    @Column(name="taskStatusEnum")
    private TaskStatusEnum taskStatus;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="userId", nullable=false)
    private User user;

    public Task() {
    }


    public Task(Long id, String taskName, String taskFinishDate, String taskExplanation, TaskStatusEnum taskStatus, User user) {
        this.id = id;
        this.taskName = taskName;
        this.taskFinishDate = taskFinishDate;
        this.taskExplanation = taskExplanation;
        this.taskStatus = taskStatus;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskFinishDate() {
        return taskFinishDate;
    }

    public void setTaskFinishDate(String taskFinishDate) {
        this.taskFinishDate = taskFinishDate;
    }

    public String getTaskExplanation() {
        return taskExplanation;
    }

    public void setTaskExplanation(String taskExplanation) {
        this.taskExplanation = taskExplanation;
    }

    public TaskStatusEnum getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatusEnum taskStatus) {
        this.taskStatus = taskStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
