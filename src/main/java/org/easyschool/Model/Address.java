package org.easyschool.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Address extends commonEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank
    @NotNull
    private int addressid;

    @NotBlank
    @NotNull
    private String address1;
    @NotBlank
    @NotNull
    private String address2;

    @NotBlank
    @NotNull
    private String city;

    @NotBlank
    @NotNull
    private String state;

    @NotBlank
    @NotNull
    @Pattern(regexp = "^[0-9]{6}$", message = "Invalid pincode format")
    private String zipcode;
}
