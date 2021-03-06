package pl.sda.projekt.cars_fleet.Services;

import com.google.common.collect.Lists;
import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;

import pl.sda.projekt.cars_fleet.model.Employee;
import pl.sda.projekt.cars_fleet.model.Task;

import java.util.List;
import java.util.stream.Collectors;


@Service

public class EmailServiceImpl {

    @Autowired
    JavaMailSender javaMailSender;


    Logger logger = LoggerFactory.getLogger(this.getClass());


    public void sendMail(Employee employee) {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setFrom("carsfleetapp@gmail.com");
        mail.setTo(employee.getEmail());
        mail.setSubject("New Task!");

        mail.setText("Dear " + employee.getFirstName().toUpperCase() + ". " + "You have to " + getTasks(employee));

        logger.info("Sending...");

        javaMailSender.send(mail);

        logger.info("Done!");
    }

    public StringBuilder getTasks(Employee employee) {
        StringBuilder tasks = new StringBuilder();
        employee.getCarUnit().getTaskSet().stream().forEach(task -> tasks.append(", " + toString()));
        return tasks;
    }


}
