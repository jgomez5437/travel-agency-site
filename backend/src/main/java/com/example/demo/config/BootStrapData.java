package com.example.demo.config;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.entities.Customer;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    public BootStrapData(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (customerRepository.count() < 4) {
            Customer rick = new Customer();
            rick.setFirstName("Rick");
            rick.setLastName("Sanchez");
            rick.setAddress("123 C-137 St");
            rick.setPostalCode("85701");
            rick.setPhone("555-0101");
            customerRepository.save(rick);

            Customer morty = new Customer();
            morty.setFirstName("Morty");
            morty.setLastName("Smith");
            morty.setAddress("123 C-137 St");
            morty.setPostalCode("85701");
            morty.setPhone("555-0102");
            customerRepository.save(morty);

            Customer summer = new Customer();
            summer.setFirstName("Summer");
            summer.setLastName("Smith");
            summer.setAddress("123 C-137 St");
            summer.setPostalCode("85701");
            summer.setPhone("555-0103");
            customerRepository.save(summer);

            Customer beth = new Customer();
            beth.setFirstName("Beth");
            beth.setLastName("Smith");
            beth.setAddress("123 C-137 St");
            beth.setPostalCode("85701");
            beth.setPhone("555-0104");
            customerRepository.save(beth);

            Customer jerry = new Customer();
            jerry.setFirstName("Jerry");
            jerry.setLastName("Smith");
            jerry.setAddress("123 C-137 St");
            jerry.setPostalCode("85701");
            jerry.setPhone("555-0105");
            customerRepository.save(jerry);

            System.out.println("Requirement I: 5 Customers bootstrapped successfully.");
        }
    }
}
