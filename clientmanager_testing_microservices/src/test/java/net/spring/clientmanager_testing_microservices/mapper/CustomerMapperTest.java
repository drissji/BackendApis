package net.spring.clientmanager_testing_microservices.mapper;

import lombok.extern.slf4j.Slf4j;
import net.spring.clientmanager_testing_microservices.dto.CustomerDTO;
import net.spring.clientmanager_testing_microservices.entities.Customer;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class CustomerMapperTest {


   //Not Spring component so no need for autowired
    CustomerMapper customerMapper = new CustomerMapper();

    @Test
    void fromCustomer() {
        Customer givenCustomer = Customer.builder().id(1L).firstName("leo").lastName("jon").email("leo@et.esiea.fr").build();
        CustomerDTO expected = CustomerDTO.builder().id(1L).firstName("leo").lastName("jon").email("leo@et.esiea.fr").build();

        CustomerDTO result = customerMapper.fromCustomer(givenCustomer);
        assertThat(expected).usingRecursiveComparison().isEqualTo(result);
        assertThat(result).isNotNull();
    }

    @Test
    void fromCustomerDTO() {
        CustomerDTO  customerDTO = CustomerDTO.builder().id(1L).firstName("leo").lastName("jon").email("leo@et.esiea.fr").build();
        Customer expected = Customer.builder().id(1L).firstName("leo").lastName("jon").email("leo@et.esiea.fr").build();

        Customer result = customerMapper.fromCustomerDTO(customerDTO);
        assertThat(expected).usingRecursiveComparison().isEqualTo(result);
        assertThat(result).isNotNull();
    }

    @Test
    void testFromCustomer() {
        List<Customer> customers = List.of(
                Customer.builder().id(1L).firstName("leo").lastName("jon").email("leo@et.fr").build(),
                Customer.builder().id(1L).firstName("lucien").lastName("manu").email("mm@et.fr").build(),
                Customer.builder().id(1L).firstName("chatrine").lastName("jj").email("chatrin@et.fr").build()
        );
        List<CustomerDTO> customerDTOS = customerMapper.fromCustomer(customers);

        assertThat(customers).usingRecursiveComparison().isEqualTo(customerDTOS);
        assertThat(customerDTOS).isNotNull();

    }
}