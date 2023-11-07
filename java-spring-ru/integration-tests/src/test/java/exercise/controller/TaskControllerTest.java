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
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// END
@SpringBootTest
@AutoConfigureMockMvc
// BEGIN
public class TaskControllerTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Test
    public void testShow() throws Exception {

        var task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.beer().brand())
                .supply(Select.field(Task::getDescription), () -> faker.beer().name())
                .create();
        taskRepository.save(task);

        var result = mockMvc.perform(get("/tasks/" + task.getId()))
                .andExpect(status().isOk())
                .andReturn();

//        var body = result.getResponse().getContentAsString();
//        assertThatJson(body).isObject().isEqualTo(task);

    }

    @Test
    public void testCreate() throws Exception {
        var task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.beer().brand())
                .supply(Select.field(Task::getDescription), () -> faker.beer().name())
                .create();
        taskRepository.save(task);

        var data = new HashMap<>();
        data.put("title", task.getTitle());
        data.put("description", task.getDescription());

        var request = put("/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                // ObjectMapper конвертирует Map в JSON
                .content(om.writeValueAsString(data));

        var result = mockMvc.perform(request)
                .andExpect(status().isOk()).andReturn();

        var body = result.getResponse().getContentAsString();

//        assertThatJson(body).isObject().isEqualTo(task);

//        task = taskRepository.findById(task.getId()).get();
//        assertThat(task.getTitle()).isEqualTo((task.getTitle()));
//        assertThat(task.getDescription()).isEqualTo((task.getDescription()));

    }

    @Test
    public void testDelete() throws Exception {

        var task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.beer().brand())
                .supply(Select.field(Task::getDescription), () -> faker.beer().name())
                .create();
        taskRepository.save(task);

        mockMvc.perform(delete("/tasks/" + task.getId()))
                .andExpect(status().isOk())
                .andReturn();

        Optional<Task> taskById = taskRepository.findById(task.getId());
        assertThat(taskById).isEmpty();

    }

    @Test
    public void testUpdate() throws Exception {
        var task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.beer().brand())
                .supply(Select.field(Task::getDescription), () -> faker.beer().name())
                .create();
        taskRepository.save(task);

        var data = new HashMap<>();
        data.put("title", "myTitle");
        data.put("description", "myDescription");

        var request = put("/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                // ObjectMapper конвертирует Map в JSON
                .content(om.writeValueAsString(data));

        mockMvc.perform(request)
                .andExpect(status().isOk());

        task = taskRepository.findById(task.getId()).get();
        assertThat(task.getTitle()).isEqualTo(("myTitle"));
        assertThat(task.getDescription()).isEqualTo(("myDescription"));
    }


}
// END

