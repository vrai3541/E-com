package customers.service.imp;

import customers.entity.Customer;
import customers.entity.Role;
import customers.repo.CustomerRepository;
import customers.service.CustomerDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomerDetailServiceImpl implements CustomerDetailService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    // use @Transactional(readOnly = true) if code doesn't works
    public UserDetails loadUserByUsername(String username) {
        Customer customer = customerRepository.findByUsername(username);
        if (customer == null) throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        for (Role role : customer.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(customer.getUsername(), customer.getPassWord(), grantedAuthorities);

    }
}
