package ra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ApiSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiSecurityApplication.class,args);
    }

}
