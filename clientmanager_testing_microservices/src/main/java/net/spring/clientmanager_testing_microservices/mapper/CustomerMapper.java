package net.spring.clientmanager_testing_microservices.mapper;

import net.spring.clientmanager_testing_microservices.dto.CustomerDTO;
import net.spring.clientmanager_testing_microservices.entities.Customer;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerMapper {

    private ModelMapper modelMapper = new ModelMapper();


    //From customer entity to customer dto
    public CustomerDTO fromCustomer(Customer customer){
        return modelMapper.map(customer, CustomerDTO.class);
    }
    //From customer dto to customer entity
    public Customer fromCustomerDTO(CustomerDTO customerDTO){
        return modelMapper.map(customerDTO, Customer.class);
    }
    //From customer list to customerDTO list
    public List<CustomerDTO> fromCustomer(List<Customer> customerList){
        return customerList.stream().map(c ->modelMapper.map(c,CustomerDTO.class)).collect(Collectors.toList());
    }

}
