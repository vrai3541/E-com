package customers.service;

import customers.entity.Customer;

public interface CustomerService {

    void save(Customer customer);

    Customer findByUsername(String username);
}
