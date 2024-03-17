package com.example.todolist.controller;

import com.example.todolist.dto.TodoForm;
import com.example.todolist.entity.TodoEntity;
import com.example.todolist.repository.TodoEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class TodoController {
    @Autowired
    private TodoEntityRepository todoEntityRepository;
    @GetMapping("/")
    public String newTodoForm() {
        return "index";
    }

    @PostMapping("/create")
    public String createArticle(TodoForm form) {
        log.info(form.toString());
        // 1. DTO를 엔티티로 변환
        TodoEntity todoentity = form.toEntity();
        log.info(todoentity.toString());
        // 2. 리파지터리로 엔티티를 DB에 저장
        TodoEntity saved = todoEntityRepository.save(todoentity);
        log.info(saved.toString());
        return "redirect:/";
    }
}
