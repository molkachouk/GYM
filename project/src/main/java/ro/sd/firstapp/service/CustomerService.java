package ro.sd.firstapp.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ro.sd.firstapp.model.Customer;
import ro.sd.firstapp.model.dto.CustomerDTO;
import ro.sd.firstapp.model.dto.GymDTO;
import ro.sd.firstapp.model.mapper.CustomerMapper;
import ro.sd.firstapp.model.mapper.GymMapper;
import ro.sd.firstapp.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service for customer operations
 */
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final static Logger logger = LoggerFactory.getLogger(CustomerService.class.getName());

    /**
     * Searches for an customer in the database by username
     *
     * @param username of the customer
     * @return the found customer
     */
    public Customer findByUsername(String username) {
        logger.info("Find customer: {}", username);

        Optional<Customer> customer = customerRepository.findByUsername(username);

        return customer.orElse(null);
    }

    /**
     * Registers a customer
     *
     * @param customerDTO, the details of the customer
     * @return the registered customer
     */
    public CustomerDTO register(CustomerDTO customerDTO) {
        Customer c = CustomerMapper.getInstance().convertFromDTO(customerDTO);
        customerRepository.save(c);
        logger.info("Register customer: {}", customerDTO.getUsername());

        return CustomerMapper.getInstance().convertToDTO(c);
    }

    public List<CustomerDTO> findAll() {
        logger.info("Find all customers from the database");
        return customerRepository.findAll().stream()
                .map(CustomerMapper.getInstance()::convertToDTO)
                .collect(Collectors.toList());
    }
}
