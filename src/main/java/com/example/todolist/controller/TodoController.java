package com.example.todolist.controller;

import com.example.todolist.dto.TodoForm;
import com.example.todolist.entity.TodoEntity;
import com.example.todolist.repository.TodoEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class TodoController {
    @Autowired
    private TodoEntityRepository todoEntityRepository;

    @PostMapping("/create")
    public String createTodo(TodoForm form) {
        log.info(form.toString());
        // 1. DTO를 엔티티로 변환
        TodoEntity todoentity = form.toEntity();
        log.info(todoentity.toString());
        // 2. 리파지터리로 엔티티를 DB에 저장
        TodoEntity saved = todoEntityRepository.save(todoentity);
        log.info(saved.toString());
        return "redirect:/";
    }

    @GetMapping("/")
    public String readTodo(Model model) {
        // 1. 모든 데이터 가져오기
        Iterable<TodoEntity> todoEntityList = todoEntityRepository.findAll();
        int index = 0;
        List<Map<String, Object>> todosWithIndex = new ArrayList<>();
        for (TodoEntity todo : todoEntityList) {
            Map<String, Object> todoMap = new HashMap<>();
            todoMap.put("index", ++index);
            todoMap.put("id", todo.getId());
            todoMap.put("task", todo.getTask());
            todoMap.put("status", todo.getStatus());
            // 여기에 다른 필요한 Todo 속성들을 추가할 수 있습니다.
            todosWithIndex.add(todoMap);
        }
        // 2. 모델에 데이터 등록하기
        model.addAttribute("todos", todosWithIndex);
        // 3. 뷰 페이지 설정하기
        return "index";
    }

    @PostMapping("/update")
    public String editTodo(@RequestParam("id") Long id) {
        // 1. 엔티티를 DB에서 찾은 후 status 변경
        TodoEntity target = todoEntityRepository.findById(id).orElse(null);
        target.setStatus("Completed");
        // 2. 수정 내용 저장
        todoEntityRepository.save(target);
        // 3. 리다이렉트하기
        log.info(target.toString());
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteTodo(@RequestParam("id") Long id) {
        log.info("삭제 요청이 들어왔습니다!");
        // 1. 삭제할 대상 가져오기
        TodoEntity target = todoEntityRepository.findById(id).orElse(null);
        log.info(target.toString());
        // 2. 대상 엔티티 삭제하기
        if (target != null) {
            todoEntityRepository.delete(target);
        }
        // 3. 결과 페이지로 리다이렉트하기
        return "redirect:/";
    }
}
