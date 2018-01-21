import com.tylkowski.RestService;
import com.tylkowski.entity.Group;
import com.tylkowski.entity.Student;
import com.tylkowski.service.GroupService;
import com.tylkowski.service.StudentService;
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

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestService.class)
@WebAppConfiguration
public class GroupTest {
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

    private Group groupToTests = new Group("testowaGrupa");

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
    public void createGroupWithServiceTest() {
        Group groupTemp = groupService.save(groupToTests);
        assertNotNull(groupTemp);
        groupService.delete(groupTemp.getId());
    }

    @Test
    public void deleteGroupWithServiceTest() {
        long groupId = groupService.save(groupToTests).getId();
        groupService.delete(groupId);
        assertNull(groupService.findOne(groupId));
    }

    @Test
    public void countGroupsWithServiceTest() {
        assertNotEquals(groupService.count(), -1);
    }

    @Test
    public void postGroupRestTest() throws Exception {
        String groupJson = new JSONObject()
                .put("groupName", "testowa").toString();

        String urlToAddedGroup = this.mockMvc.perform(post("/api/groups")
                .contentType(contentType)
                .content(groupJson))
                .andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");
        long groupId = Long.parseLong(urlToAddedGroup.replace("http://localhost/api/groups/", ""));
        groupService.delete(groupId);
    }

    @Test
    public void getGroupsList() throws Exception {
        this.mockMvc.perform(get("/api/groups")).andExpect(status().isOk());
    }

    @Test
    public void getOneGroupRestTest() throws Exception {
        ArrayList<Group> groupArrayList = (ArrayList<Group>) groupService.findAll();
        long groupId = groupArrayList.get(0).getId();
        this.mockMvc.perform(get("/api/groups/" + groupId)).andExpect(status().isOk());
    }

    @Test
    public void getGroupStudentsRestTest() throws Exception {
        ArrayList<Group> groupArrayList = (ArrayList<Group>) groupService.findAll();
        long groupId = groupArrayList.get(0).getId();
        this.mockMvc.perform(get("/api/groups/" + groupId + "/students")).andExpect(status().isOk());
    }

    @Test
    public void deleteGroupRestTest() throws Exception {
        long groupId = groupService.save(new Group("testowa")).getId();

        this.mockMvc.perform(delete("/api/groups/delete/" + groupId)).andExpect(status().isNoContent());
    }

    @Test
    public void deleteGroupFromStudentRestTest() throws Exception {
        long groupId = groupService.save(groupToTests).getId();
        List<Group> groups = new LinkedList<>();
        groups.add(groupToTests);
        long studentId = studentService.save(new Student("Ziomek", "do testow", groups)).getId();

        this.mockMvc.perform(delete("/api/groups/" + groupId + "/remove/" + studentId)).andExpect(status().isNoContent());
        this.mockMvc.perform(delete("/api/students/delete/" + studentId)).andExpect(status().isNoContent());
        this.mockMvc.perform(delete("/api/groups/delete/" + groupId)).andExpect(status().isNoContent());
    }

    @Test
    public void updateGroupRestTest() throws Exception {
        long groupId = groupService.save(groupToTests).getId();
        String groupJson = new JSONObject()
                .put("id", groupId)
                .put("groupName", "testowa2").toString();

        this.mockMvc.perform(put("/api/groups/update/" + groupId)
                .contentType(contentType)
                .content(groupJson))
                .andExpect(status().isOk());

        this.mockMvc.perform(delete("/api/groups/delete/" + groupId)).andExpect(status().isNoContent());
    }
}
