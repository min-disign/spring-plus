package org.example.expert.domain.comment.repository;

import org.example.expert.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // @EntityGraph를 사용하여 Todo와 User를 함께 로드
    @EntityGraph(attributePaths = {"todo", "user"})
    List<Comment> findByTodoId(Long todoId);
}
