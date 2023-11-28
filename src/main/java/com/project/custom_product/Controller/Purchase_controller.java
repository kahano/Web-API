package com.project.custom_product.Controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.custom_product.DTO.PurchaseDTO;
import com.project.custom_product.Service.Service_Impl.Purchase_serviceImpl;
import com.project.custom_product.entities.Purchase;



@RestController
@RequestMapping("/purchases")
public class Purchase_controller {

    @Autowired
    private Purchase_serviceImpl purchase_service;

    
    @Autowired
    private ModelMapper mapper;


    private  PurchaseDTO convertEntityToDto(Purchase purchase){
        

        PurchaseDTO purchaseDTO = mapper.map(purchase, PurchaseDTO.class);
        return purchaseDTO;
    }


    @PostMapping("/customer/{customer_id}/product/{product_id}") // creating an order for a customer 
    public ResponseEntity<PurchaseDTO> create(@RequestBody PurchaseDTO purchase_dto, @PathVariable Integer customer_id, @PathVariable Integer product_id){

        Purchase purchase = mapper.map(purchase_dto, Purchase.class);
        purchase_service.savePurchase(purchase,customer_id,product_id);
        
        return new ResponseEntity<>(purchase_dto,HttpStatus.CREATED);

    }

    @GetMapping("/customer/{customer_id}/product/{product_id}") // showing the order to the customer 
    public ResponseEntity<PurchaseDTO> getPurchase(@PathVariable Integer customer_id, @PathVariable Integer product_id){

        Purchase purchase = purchase_service.findPurchaseBYId(customer_id, product_id);

        PurchaseDTO purchase_dto = convertEntityToDto(purchase);

        return new ResponseEntity<>(purchase_dto,HttpStatus.OK);

    }

    @PutMapping("/update/customer/{customer_id}/product/{product_id}") // updating the number of orders
    public ResponseEntity<PurchaseDTO> update(@RequestBody PurchaseDTO purchase_dto, @PathVariable Integer customer_id, 
           @PathVariable Integer product_id){

            Purchase purchase = mapper.map(purchase_dto, Purchase.class);
            purchase_service.updatePurchase(customer_id, product_id, purchase.getTotal_quantities());
            PurchaseDTO dto = convertEntityToDto(purchase);

            return new ResponseEntity<>(dto,HttpStatus.OK);
    }


    @GetMapping("/totalprice/customer/{customer_id}/product/{product_id}") // showing the bill after ordering

    public ResponseEntity<PurchaseDTO> getBillForPurchase(@PathVariable Integer customer_id, @PathVariable Integer product_id){

        Purchase purchase= purchase_service.getTotalPricePurchase_per_Customer(customer_id,product_id);

        PurchaseDTO dto = convertEntityToDto(purchase);
        

        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    



    




    
}
