package pl.sda.projekt.cars_fleet.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.sda.projekt.cars_fleet.Services.EmployeeService;
import pl.sda.projekt.cars_fleet.config.WebSecurityConfig;
import pl.sda.projekt.cars_fleet.model.Employee;
import pl.sda.projekt.cars_fleet.model.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Import(WebSecurityConfig.class)
public class EmployeeControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    EmployeeService employeeService;

    ObjectMapper mapper = new ObjectMapper();

    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    @Test
    public void givenEmployees_whenAddEmployee_thenReturnCreatedEmployee() throws Exception {


        Employee employee = new Employee();
        employee.setId(1l);
        employee.setFirstName("Antonio");
        employee.setLastName("Facaldo");
        employee.setEmail("antonio@o2.pl");
        employee.setLogin("antonio");
        employee.setPassword("antonio");
        employee.setActive(1);

        given(employeeService.addNewEmployee(any(Employee.class))).willReturn(employee);

        mvc.perform(post("/employees/addEmployee")
                .content(mapper.writeValueAsString(employee))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is(employee.getFirstName())));
    }
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    @Test
    public void getAllEmployees() throws Exception {
        Employee employee = new Employee();
        employee.setId(1l);
        employee.setFirstName("Antonio");
        employee.setLastName("Facaldo");
        employee.setEmail("antonio@o2.pl");
        employee.setLogin("antonio");
        employee.setPassword("antonio");
        employee.setActive(1);
        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(employee);

        given(employeeService.getAllEmployees()).willReturn(employeeList);

        mvc.perform(get("/employees/getAllEmployees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", Matchers.is(employee.getFirstName())));
    }

    @Test
    public void getEmployeeById() {
    }

    @Test
    public void getEmployeeByLastName() {
    }

    @Test
    public void getEmployeeByFirstName() {
    }

    @Test
    public void deleteEmployee() {
    }

    @Test
    public void updateEmployee() {
    }

    @Test
    public void addAdminRole() {
    }
}
