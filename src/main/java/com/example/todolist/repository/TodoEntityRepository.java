package com.example.todolist.repository;

import com.example.todolist.entity.TodoEntity;
import org.springframework.data.repository.CrudRepository;

public interface TodoEntityRepository extends CrudRepository<TodoEntity, Long> {

}
