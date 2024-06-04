package com.todo_list.todo_list_my_artifact.models;

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
    private User autor;

    @Column( nullable = false )
    private String title;

    @Column( nullable = false)
    private String desciption;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false )
    private Date task_date;

    @ManyToOne
    @JoinColumn(name = "assignedto_id", nullable = false )
    private User assignedTo;

}
