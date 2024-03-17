package com.example.todolist.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class TodoEntity {
    @Id
    @GeneratedValue
    public Long id;
    @Column
    public String task;
    @Column
    public String status;
}
