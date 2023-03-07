package me.zeeeeeeek.backend.controllers.task;

import lombok.RequiredArgsConstructor;
import me.zeeeeeeek.backend.models.user.User;
import me.zeeeeeeek.backend.services.task.TaskService;
import me.zeeeeeeek.backend.util.AuthUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tasks/")
public class TaskRestController {
    private final TaskService taskService;

    private final AuthUtil authUtil;

    @PutMapping("tasks/{taskId}")
    public ResponseEntity<String> setTaskIsCompleted(@PathVariable UUID taskId, @RequestParam boolean completed, Authentication authentication) {
        User owner = authUtil.getUserFromAuthentication(authentication);
        try {
            this.taskService
                    .setIsCompleted(taskId, completed, owner);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("tasks/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable UUID taskId, Authentication authentication) {
        User owner = authUtil.getUserFromAuthentication(authentication);
        try {
            this.taskService
                    .deleteTask(taskId, owner);
        } catch (IllegalArgumentException e) {
            ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
