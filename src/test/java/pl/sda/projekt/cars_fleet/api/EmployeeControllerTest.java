package pl.sda.projekt.cars_fleet.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import pl.sda.projekt.cars_fleet.Services.EmployeeService;
import pl.sda.projekt.cars_fleet.config.WebSecurityConfig;
import pl.sda.projekt.cars_fleet.model.Employee;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    private Employee employee;
    private Employee employee2;
    private List<Employee> employeeList;


    @Before
    public void addEmp() {
        employee = new Employee();
        employee.setId(1l);
        employee.setFirstName("antonio");
        employee.setLastName("facaldo");
        employee.setEmail("antonio@o2.pl");
        employee.setLogin("antonio");
        employee.setPassword("antonio");
        employee.setActive(1);

        employee2 = new Employee();
        employee.setId(2l);
        employee.setFirstName("joseph");
        employee.setLastName("facaldo");
        employee.setEmail("joseph@o2.pl");
        employee.setLogin("Josu");
        employee.setPassword("allabama");
        employee.setActive(1);


        employeeList = Arrays.asList(employee, employee2);
    }

    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    @Test
    public void givenEmployees_whenAddEmployee_thenReturnCreatedEmployee() throws Exception {

        //given
        given(employeeService.addNewEmployee(any(Employee.class))).willReturn(employee);

        //when
        mvc.perform(post("/employees/addEmployee")
                .content(mapper.writeValueAsString(employee))
                .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is(employee.getFirstName())));

    }

    @WithMockUser(username = "admin", password = "admin", roles = "USER")
    @Test
    public void givenEmployees_whenAddEmployeeWithoutAdminRole_thenReturnStatus403() throws Exception {

        given(employeeService.addNewEmployee(any(Employee.class))).willReturn(employee);

        mvc.perform(post("/employees/addEmployee")
                .content(mapper.writeValueAsString(employee))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    @Test
    public void givenEmployees_whenGetAllEmployees_thenReturnListOfEmployee() throws Exception {

        given(employeeService.getAllEmployees()).willReturn(employeeList);

        mvc.perform(get("/employees/getAllEmployees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", Matchers.is(employee.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName", Matchers.is(employee2.getFirstName())));
    }

    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    @Test
    public void givenEmployees_whenGetEmployeeById_thenReturnEmployeeById() throws Exception {
        given(employeeService.getEmployeeById(0l)).willReturn(employee);

        mvc.perform(get("/employees/getEmployeeById{id}", 0l)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is(employee.getFirstName())));

    }

    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    @Test
    public void givenEmployees_whenGetEmployeesByFirstName_thenReturnEmployeesListByFirstName() throws Exception {
        List<Employee> antonioList = Arrays.asList(employee);

        given(employeeService.getEmployeeByFirstName("Antonio".toLowerCase())).willReturn(antonioList);

        mvc.perform(get("/employees/getEmployeesByFirstName{firstName}", "Antonio")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
    }

    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    @Test
    public void givenEmployees_whenGetEmployeesByLastName_thenReturnEmployeesListByLastName() throws Exception {

        List<Employee> facaldoList = Arrays.asList(employee, employee2);

        given(employeeService.getEmployeeByLastName("Facaldo".toLowerCase())).willReturn(facaldoList);

        mvc.perform(get("/employees/getEmployeesByLastName{lastName}", "Facaldo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
    }

    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
    @Test
    public void deleteEmployee() throws Exception {

    }

    @Test
    public void updateEmployee() {
    }

    @Test
    public void addAdminRole() {
    }
}
