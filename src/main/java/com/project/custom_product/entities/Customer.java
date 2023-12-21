package com.project.custom_product.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;





@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "customer")
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Nonnull
    @Column(name = "first_name", nullable = false)
    private String first_name;

    @Nonnull
     @Column(name = "last_name", nullable = false)
    private String last_name;


    private String password;

   
    @Column(name = "address")
    private String address;

  
    @Column(name = "telefon_number")
    private String telefon_number;

    @JsonIgnore
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL )
    private List<Purchase> purchases ;

    

    


 }
