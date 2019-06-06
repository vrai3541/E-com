package customers.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private int addressId;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "postal_code")
    private String postCode;

    @Column(name = "city")
    private String city;

    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name="customerId", nullable=false)
    private Customer address;
}
