package lk.ijse.gdse.springboot.notetaker.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "lk.ijse.gdse.springboot.notetaker")
@EnableWebMvc
public class WebAppConfig {
}
