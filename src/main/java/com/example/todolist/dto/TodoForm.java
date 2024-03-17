package com.example.todolist.dto;

import com.example.todolist.entity.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class TodoForm {
    private Long id;
    private String task;
    private String status;


    public TodoEntity toEntity() {
        TodoEntity todoEntity = new TodoEntity(id, task, status);
        todoEntity.setTask(this.task);
        todoEntity.setStatus("In progress");
        return todoEntity;
    }
}
