package customers.resource;

import customers.entity.Customer;
import customers.request.RegistrationRequest;
import customers.service.CustomerService;
import customers.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import validator.CustomerValidator;

public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private CustomerValidator customerValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new Customer());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@RequestBody RegistrationRequest registrationRequest,BindingResult bindingResult) {
        customerValidator.validate(registrationRequest, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        Customer customer = new Customer();
        customer.setUsername(registrationRequest.getUserName());
        customer.setPassWord(registrationRequest.getPassWord());


        customerService.save(customer);

        securityService.autoLogin(registrationRequest.getUserName(), registrationRequest.getPassWord());


        return "redirect:/welcome";
    }
}
