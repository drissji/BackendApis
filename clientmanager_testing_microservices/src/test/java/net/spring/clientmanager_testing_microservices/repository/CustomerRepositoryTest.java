package net.spring.clientmanager_testing_microservices.repository;

import net.spring.clientmanager_testing_microservices.entities.Customer;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.Documented;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    //Insert some data before executing tests
    @BeforeEach
    public void setUp(){
               customerRepository.save(Customer.builder().firstName("manu").lastName("numa").email("nmu@gmail.com").build());
               customerRepository.save(Customer.builder().firstName("luca").lastName("sne").email("sne@outlook.com").build());
               customerRepository.save(Customer.builder().firstName("len").lastName("hek").email("len_hek@outlook.com").build());
    }

    @Test
    void findByEmail() {
        String email = "sne@outlook.com";
        Optional<Customer> result = customerRepository.findByEmail(email);
        System.out.println("result" + result);
        assertThat(result).isPresent();
    }

    @Test
    void shouldNotFindByEmail(){
        String email = "xxxx@xxxxx.com";
        assertThat(customerRepository.findByEmail(email)).isEmpty();
    }

    @Test
    void findByFirstNameContainingIgnoreCase() {
        List<Customer> result = customerRepository.findByFirstNameContainingIgnoreCase("a");
        List<Customer> expected = List.of(
                customerRepository.save(Customer.builder().firstName("manu").lastName("numa").email("nmu@gmail.com").build()),
                customerRepository.save(Customer.builder().firstName("luca").lastName("sne").email("sne@outlook.com").build())
        );

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(expected.size());
        //Compare value + ignore "id"
        assertThat(result).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected);
    }
}