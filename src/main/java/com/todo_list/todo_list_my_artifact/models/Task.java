package com.todo_list.todo_list_my_artifact.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name = "task" )
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "assignedto_id", nullable = false )
    @JsonIgnore
    private User assignedTo;

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
