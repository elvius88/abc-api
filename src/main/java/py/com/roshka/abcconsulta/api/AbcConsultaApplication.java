package py.com.roshka.abcconsulta.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AbcConsultaApplication {
    public static void main(String[] args) {
        SpringApplication.run(AbcConsultaApplication.class, args);
    }
}