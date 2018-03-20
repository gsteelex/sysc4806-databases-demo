package databases;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SpringBootApplication
@EnableJpaRepositories
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class Application {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("music.odb");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
