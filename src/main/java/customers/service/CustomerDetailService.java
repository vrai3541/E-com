package customers.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomerDetailService {

    UserDetails loadUserByUsername(String username);
}
