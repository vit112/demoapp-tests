package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TodoItem {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name="title")
    private String title;
    @Column(name="body")
    private String body;
    @Column(name="done")
    private boolean done;
    @JsonFormat(pattern="dd/MM/yyyy")
    @Column(name="due_date")
    private Date dueDate;
   // @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn( name = "user_id")    //*foreign key did not work, it was null*
    //private User user;

    public TodoItem(String title, boolean done, String body, Date dueDate){
        this.title = title;
        this.done = done;
        this.body = body;
        this.dueDate = dueDate;
    }


}
