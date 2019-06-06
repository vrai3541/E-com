package customers.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String passWord;

    @Transient
    private String passwordConfirm;

    @ManyToMany
    private Set<Role> roles;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "phone_no")
    private String phoneNumber;

    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER)
    public List<Address> addressList;

}
