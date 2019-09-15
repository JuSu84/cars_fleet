package pl.sda.projekt.cars_fleet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String role;

//    @ManyToMany
//    @JoinTable(
//            name = "eployee_roles",
//            joinColumns = @JoinColumn(name = "role_id"),
//            inverseJoinColumns = @JoinColumn(name = "employee_id"))
//    Set<Employee> employeeRoles;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

//    public Set<Employee> getEmployeeRoles() {
//        return employeeRoles;
//    }
//
//    public void setEmployeeRoles(Set<Employee> employeeRoles) {
//        this.employeeRoles = employeeRoles;
//    }
}

