package pl.sda.projekt.cars_fleet.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.projekt.cars_fleet.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
