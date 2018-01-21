import com.tylkowski.RestService;
import com.tylkowski.entity.Group;
import com.tylkowski.entity.Student;
import com.tylkowski.service.GroupService;
import com.tylkowski.service.StudentService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.*;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestService.class)
@WebAppConfiguration
public class StudentTest {
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;


    @Autowired
    private StudentService studentService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private GroupService groupService;
    private Student studentToTests = new Student("Hubert", "Testowy");

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        HttpMessageConverter mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }
    @Test
    public void createStudentWithService() {
        assertNotEquals(studentService.save(studentToTests).getId(), 0);
        studentService.delete(studentToTests.getId());
    }

    @Test
    public void deleteStudentWithService() {
        long studentId = studentService.save(studentToTests).getId();
        studentService.delete(studentId);
        assertNull(studentService.findOne(studentId));
    }

    @Test
    public void countStudentsTest() {
        assertNotEquals(studentService.count(), -1);
    }

    @Test
    public void postStudentRestTest() throws Exception {
        long groupId = groupService.save(new Group("testowa")).getId();
        String jsonStudent = new JSONObject()
                .put("firstName", "Ziomek")
                .put("lastName", "Do testów")
                .put("groups", new JSONArray()
                        .put(new JSONObject().put("id",groupId))).toString();


        String urlToAddedStudent = this.mockMvc.perform(post("/api/students")
                .contentType(contentType)
                .content(jsonStudent))
                .andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
        long studentId = Long.parseLong(urlToAddedStudent.replace("http://localhost/api/students/", ""));
        studentService.delete(studentId);
        groupService.delete(groupId);
        assertNull(groupService.findOne(groupId));

    }

    @Test
    public void getStudentsListRestTest() throws Exception {
        this.mockMvc.perform(get("/api/students")).andExpect(status().isOk());
    }
    @Test
    public void getOneStudentRestTest() throws Exception {
        ArrayList<Student> studentList = (ArrayList<Student>) studentService.findAll();
        long studentId = studentList.get(0).getId();
        this.mockMvc.perform(get("/api/students/" + studentId)).andExpect(status().isOk());
    }

    @Test
    public void getStudentGroupsRestTest() throws Exception {
        ArrayList<Student> studentList = (ArrayList<Student>) studentService.findAll();
        long studentId = studentList.get(0).getId();
        this.mockMvc.perform(get("/api/students/" + studentId + "/groups")).andExpect(status().isOk());
    }

    @Test
    public void deleteStudentRestTest() throws Exception {
        long studentId = studentService.save(new Student("Ziomek", "do testow")).getId();

        this.mockMvc.perform(delete("/api/students/delete/" + studentId)).andExpect(status().isNoContent());
    }

    @Test
    public void deleteStudentFromGroupRestTest() throws Exception {
        Group studentGroup = new Group("testowa");
        long groupId = groupService.save(studentGroup).getId();
        List<Group> groups = new LinkedList<>();
        groups.add(studentGroup);
        long studentId = studentService.save(new Student("Ziomek", "do testow", groups)).getId();

        this.mockMvc.perform(delete("/api/students/" + studentId + "/remove/" + groupId)).andExpect(status().isNoContent());
        this.mockMvc.perform(delete("/api/students/delete/" + studentId)).andExpect(status().isNoContent());
        this.mockMvc.perform(delete("/api/groups/delete/" + groupId)).andExpect(status().isNoContent());
    }

    @Test
    public void updateStudentRestTest() throws Exception {
        long studentId = studentService.save(new Student("imie", "nazwisko")).getId();
        long groupId = groupService.save(new Group("testowa")).getId();
        String jsonStudent = new JSONObject()
                .put("id", studentId)
                .put("firstName", "Ziomek")
                .put("lastName", "Do testów")
                .put("groups", new JSONArray()
                        .put(new JSONObject().put("id",groupId))).toString();

        this.mockMvc.perform(put("/api/students/update/" + studentId)
                .contentType(contentType)
                .content(jsonStudent))
                .andExpect(status().isOk());

        this.mockMvc.perform(delete("/api/students/delete/" + studentId)).andExpect(status().isNoContent());
        groupService.delete(groupId);
        assertNull(groupService.findOne(groupId));
    }






}
