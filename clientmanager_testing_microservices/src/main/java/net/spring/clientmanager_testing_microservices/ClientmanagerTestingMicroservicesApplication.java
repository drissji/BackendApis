package net.spring.clientmanager_testing_microservices;

import lombok.extern.slf4j.Slf4j;
import net.spring.clientmanager_testing_microservices.entities.Customer;
import net.spring.clientmanager_testing_microservices.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;


@SpringBootApplication
@Slf4j
public class ClientmanagerTestingMicroservicesApplication {

    private static final Logger logger =  LoggerFactory.getLogger(ClientmanagerTestingMicroservicesApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ClientmanagerTestingMicroservicesApplication.class, args);

    }

    @Bean
    @Profile("!test")
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository){

        /*return args -> {

            logger.info("/////////////////////////////////////");
            customerRepository.save(Customer.builder()
                    .firstName("chris").lastName("rock").email("chris@gmail.com").build());

            customerRepository.save(Customer.builder()
                    .firstName("luca").lastName("sne").email("sne@outlook.com").build());

            customerRepository.save(Customer.builder()
                    .firstName("ekke").lastName("nami").email("ekke_name@gmail.com").build());

        };*/

        return args -> {
            List<Customer> customers = List.of(
                    Customer.builder()
                            .firstName("chris").lastName("rock").email("chris@gmail.com").build(),
                    Customer.builder()
                    .firstName("luca").lastName("sne").email("sne@outlook.com").build(),
                    Customer.builder()
                    .firstName("ekke").lastName("nami").email("ekke_name@gmail.com").build()
            );

            List<Customer> savedCustomers =  customerRepository.saveAll(customers);
            log.info("Saved customers: {}", savedCustomers);

            // Fetch all customers from the database and log them
            List<Customer> allCustomers = customerRepository.findAll();
            log.info("All customers in the database: {}", allCustomers);
        };



    }


}
