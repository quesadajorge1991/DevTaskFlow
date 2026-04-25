package com.DevTaskFlow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DevTaskFlow.model.Task;
import com.DevTaskFlow.model.TaskStatus;
import com.DevTaskFlow.model.User;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByAssignee(User assignee);

    List<Task> findByStatus(TaskStatus status);

    List<Task> findByAssigneeAndStatus(User assignee, TaskStatus status);

}
