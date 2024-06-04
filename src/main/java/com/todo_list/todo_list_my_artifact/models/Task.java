package com.todo_list.todo_list_my_artifact.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "task" )
public class Task {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    @JsonIgnore
    private User autor;

    @Column( nullable = false )
    private String title;

    @Column( nullable = false)
    private String desciption;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false )
    private Date task_date;

//    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "assignedto_id", nullable = false )
    @JsonIgnore
    private User assignedTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAutor() {
        return autor;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public Date getTask_date() {
        return task_date;
    }

    public void setTask_date(Date task_date) {
        this.task_date = task_date;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", autor=" + autor +
                ", title='" + title + '\'' +
                ", desciption='" + desciption + '\'' +
                ", task_date=" + task_date +
                ", assignedTo=" + assignedTo +
                '}';
    }
}
