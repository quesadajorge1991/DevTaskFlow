package com.DevTaskFlow.service;

import com.DevTaskFlow.model.Comment;
import com.DevTaskFlow.model.Task;
import com.DevTaskFlow.model.User;
import com.DevTaskFlow.repository.CommentRepository;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    
    public Comment addComment(String content, Task task, User author) {
        Comment comment = Comment.builder()
            .content(content)
            .task(task)
            .author(author)
            .build();
        return commentRepository.save(comment);
    }
    
    public List<Comment> findCommentsByTask(Task task) {
        return commentRepository.findByTaskOrderByCreatedAtDesc(task);
    }
    
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
    
    public Comment findCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

}
