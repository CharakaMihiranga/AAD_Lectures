package lk.ijse.gdse.springboot.notetaker.config;

import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "lk.ijse.gdse.springboot.notetaker")
@EnableWebMvc //this annotation is used to import the Spring MVC configuration
@EnableJpaRepositories(basePackages = "lk.ijse.gdse.springboot.notetaker") //this annotation is used to import the JPA configuration
@EnableTransactionManagement //this annotation is used to import the transaction management configuration
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, //2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
) //this annotation used to configure the file upload size limits
public class WebAppConfig {
}


