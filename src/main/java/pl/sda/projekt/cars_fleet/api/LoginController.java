package pl.sda.projekt.cars_fleet.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.sda.projekt.cars_fleet.config.WebSecurityConfig;
import pl.sda.projekt.cars_fleet.model.Employee;
import pl.sda.projekt.cars_fleet.model.Login;

import javax.sql.DataSource;

@RestController
@RequestMapping("/login/")
public class LoginController {


//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Autowired
//    private DataSource dataSource;
//
//    @PutMapping("{login}{password}")
//    public void login(AuthenticationManagerBuilder auth, @PathVariable("login") String login, @PathVariable("password") String password)             throws Exception {
//
//}
}
