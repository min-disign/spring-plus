package org.example.expert.domain.todo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.common.annotation.Auth;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.todo.dto.request.TodoSaveRequest;
import org.example.expert.domain.todo.dto.response.TodoResponse;
import org.example.expert.domain.todo.dto.response.TodoSaveResponse;
import org.example.expert.domain.todo.service.TodoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos")
    public ResponseEntity<TodoSaveResponse> saveTodo(
            @Auth AuthUser authUser,
            @Valid @RequestBody TodoSaveRequest todoSaveRequest
    ) {
        return ResponseEntity.ok(todoService.saveTodo(authUser, todoSaveRequest));
    }

    @GetMapping("/todos")
    public ResponseEntity<Page<TodoResponse>> getTodos(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntpackage org.example.expert.domain.todo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.common.annotation.Auth;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.todo.dto.request.TodoSaveRequest;
import org.example.expert.domain.todo.dto.response.TodoResponse;
import org.example.expert.domain.todo.dto.response.TodoSaveResponse;
import org.example.expert.domain.todo.service.TodoService;
import org.example.expert.domain.common.exception.InvalidRequestException;
import org.example.expert.domain.common.dto.ApiErrorResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        @RestController
        @RequiredArgsConstructor
        public class TodoController {

            private final TodoService todoService;

            @PostMapping("/todos")
            public ResponseEntity<TodoSaveResponse> saveTodo(
                    @Auth AuthUser authUser,
                    @Valid @RequestBody TodoSaveRequest todoSaveRequest
            ) {
                return ResponseEntity.ok(todoService.saveTodo(authUser, todoSaveRequest));
            }

            @GetMapping("/todos")
            public ResponseEntity<Page<TodoResponse>> getTodos(
                    @RequestParam(defaultValue = "1") int page,
                    @RequestParam(defaultValue = "10") int size,
                    @RequestParam(required = false) String weather,  // weather 조건 추가
                    @RequestParam(required = false) String startDate, // 기간 시작일 조건 추가
                    @RequestParam(required = false) String endDate    // 기간 종료일 조건 추가
            ) {
                return ResponseEntity.ok(todoService.getTodos(page, size, weather, startDate, endDate));
            }


            @GetMapping("/todos/{todoId}")
            public ResponseEntity<TodoResponse> getTodo(@PathVariable long todoId) {
                return ResponseEntity.ok(todoService.getTodo(todoId));
            }

            // 예외 처리 핸들러 추가
            @ExceptionHandler(InvalidRequestException.class)
            public ResponseEntity<ApiErrorResponse> handleInvalidRequestException(InvalidRequestException ex) {
                ApiErrorResponse errorResponse = new ApiErrorResponse(
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.name(),
                        ex.getMessage()
                );
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }
        }
        ity.ok(todoService.getTodos(page, size));
    }

    @GetMapping("/todos/{todoId}")
    public ResponseEntity<TodoResponse> getTodo(@PathVariable long todoId) {
        return ResponseEntity.ok(todoService.getTodo(todoId));
    }
}
