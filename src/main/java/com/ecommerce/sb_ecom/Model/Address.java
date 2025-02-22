package com.ecommerce.sb_ecom.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.pl.NIP;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long AddressId;

    @NotBlank
    @Size(min = 5,message = "Street name must be at least 5 characters")
    private String street;

    @NotBlank
    @Size(min = 4,message = "Building Name must be at least 4 characters")
    private String buildingName;

    @NotBlank
    @Size(min = 2,message = "City name must be at least 2 characters")
    private String city;

    @NotBlank
    @Size(min = 2,message = "State name must be at least 2 characters")
    private String state;

    @NotBlank
    @Size(min = 2,message = "Country name must be at least 2 characters")
    private String country;

    @NotBlank
    @Size(min = 6,message = "Pincode name must be at least 6 characters")
    private String pinCode;

    @ToString.Exclude
    @ManyToMany(mappedBy = "addresses")
    private List<User> users = new ArrayList<>();

    public Address(String street, String buildingName, String city, String state, String country, String pinCode) {
        this.street = street;
        this.buildingName = buildingName;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pinCode = pinCode;
    }
}
