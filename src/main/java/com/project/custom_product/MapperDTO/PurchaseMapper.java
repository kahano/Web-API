package com.project.custom_product.MapperDTO;

import org.springframework.stereotype.Component;

import com.project.custom_product.DTO.PurchaseDTO;

import com.project.custom_product.entities.Purchase;

@Component
public class PurchaseMapper {
    
    public PurchaseDTO convertPurchaseToDto(Purchase purchase){
        
        return PurchaseDTO.builder()
        .purchase_code(purchase.getPurchase_code())
        .price(purchase.getPrice())
        .total_quantities(purchase.getTotal_quantities())
        .build();
    }

    public Purchase convertPurchaseDTO_ToPurchase(PurchaseDTO purchase){
        
        return Purchase.builder()
        .purchase_code(purchase.getPurchase_code())
        .price(purchase.getPrice())
        .total_quantities(purchase.getTotal_quantities())
        .build();
    }


    
}
