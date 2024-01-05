package com.project.custom_product.entities;


import java.util.concurrent.atomic.AtomicInteger;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



@Entity
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@Table(name = "purchase", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"customer_id", "product_id"})
})

public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    private Integer id;

    @Nonnull
    @Column(name = "purchase_code", unique = true)
    private String purchase_code;

    @Nonnull
    @Column(name = "price" , nullable = false)
    private Integer price;

    @Nonnull
    @Column(name = "total_quantities", nullable = false)
    private Integer total_quantities;


    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public void setTotal_quantities(Integer amount){
        if(product.getTotal_quantities() > 0 && product.getTotal_quantities() >= amount ){
            this.total_quantities = amount;
            product.setTotal_quantities(product.getTotal_quantities()- amount); // update the remaining products
           
        }
        else{
            throw new IllegalArgumentException("The value exceeded max products available"); // if products finished we need to update mount of products in product clas
        }
    }

    public Integer getTotal_quantities(){
        return this.total_quantities;
    }


    


    
}
