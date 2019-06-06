package customers.service.imp;

import customers.entity.Customer;
import customers.entity.Role;
import customers.repo.CustomerRepository;
import customers.repo.RoleRepository;
import customers.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;


@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(Customer customer) {
        customer.setPassWord(bCryptPasswordEncoder.encode(customer.getPassWord()));
        customer.setRoles(new HashSet<Role>(roleRepository.findAll()));
        customerRepository.save(customer);
    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

}
