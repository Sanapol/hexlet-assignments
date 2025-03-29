package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    @Test
    public void testShow() throws Exception {
        var task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().sentence(5))
                .create();
        taskRepository.save(task);

        var result = mockMvc.perform(get("/tasks/" + task.getId()))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).and(a -> a.node("title").isEqualTo(task.getTitle()),
                a -> a.node("description").isEqualTo(task.getDescription()));
    }

    @Test
    public void createTest() throws Exception {
        var data = new HashMap<>();
        data.put("title", "Book");
        data.put("description", "I am description");

        var result = mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(data)))
                .andExpect(status().isCreated());

        var task = taskRepository.findByTitle("Book");
        assertThat(task.get().getDescription()).isEqualTo("I am description");
        assertThat(task.get().getId()).isNotNull();
        assertThat(task.get().getCreatedAt()).isNotNull();
    }

    @Test
    public void updateTest() throws Exception {
        var task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().sentence(5))
                .create();
        taskRepository.save(task);

        var data = new HashMap<>();
        data.put("title", "new title");
        data.put("description", "new description");

        var result = mockMvc.perform(put("/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(data)))
                .andExpect(status().isOk());

        var update = taskRepository.findById(task.getId()).get();
        assertThat(update.getTitle()).isEqualTo("new title");
        assertThat(update.getDescription()).isEqualTo("new description");
    }

    @Test
    public void deleteTest() throws Exception {
        var task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .create();
        taskRepository.save(task);

        var result = mockMvc.perform(delete("/tasks/" + task.getId()))
                .andExpect(status().isOk());

        var check = taskRepository.findById(task.getId());
        assertThat(check).isNotPresent();
    }
    // END
}
