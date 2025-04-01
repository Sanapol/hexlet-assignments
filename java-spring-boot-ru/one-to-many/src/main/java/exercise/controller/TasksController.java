package exercise.controller;

import java.util.List;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.mapper.TaskMapper;
import exercise.model.Task;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.TaskRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired TaskMapper taskMapper;
    // BEGIN
    @GetMapping(path = "")
    public List<TaskDTO> index() {
        return taskRepository.findAll().stream().map(t -> taskMapper.map(t)).toList();
    }

    @GetMapping(path = "/{id}")
    public TaskDTO show(@PathVariable long id) {
        var task = taskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("task with id " + id + " not found"));
        return taskMapper.map(task);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO create(@Valid @RequestBody TaskCreateDTO data) {
        var task = taskMapper.map(data);
        var user = userRepository.findById(task.getAssignee().getId()).orElseThrow(() -> new RuntimeException("da"));
        user.addTask(task);
        taskRepository.save(task);
        userRepository.save(user);
        return taskMapper.map(task);
    }

    @PutMapping(path = "/{id}")
    public TaskDTO update(@PathVariable long id, @Valid @RequestBody TaskUpdateDTO data) {
        var task = taskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("task with id " + id + " not found"));
        task.getAssignee().removeTask(task);
        taskMapper.update(data, task);
        var user = userRepository.findById(task.getAssignee().getId()).orElseThrow(() ->
                new ResourceNotFoundException("da"));
        user.addTask(task);
        taskRepository.save(task);
        userRepository.save(user);
        return taskMapper.map(task);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        var task = taskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("task with id " + id + " not found"));
        task.getAssignee().removeTask(task);
        taskRepository.delete(task);
    }
    // END
}
