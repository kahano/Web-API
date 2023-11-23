package com.project.custom_product.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    private long id;
    private String first_name;
    private String last_name;
    private String password;
    private String address;
    private String telefon_number;
}
