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
    private User autor;


    @Column( nullable = false )
    private String title;

    @Column( nullable = false)
    private String desciption;

    @Temporal(TemporalType.DATE)
    @Column(name = "task_date", nullable = false )
    private Date taskDate;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "assignedto_id", nullable = false )
    private User assignedTo;

    @Column(name = "is_completed", nullable = false )
    private boolean completed;


}
