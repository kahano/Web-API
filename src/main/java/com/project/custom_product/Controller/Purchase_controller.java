package com.project.custom_product.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.MapperDTO.PurchaseMapper;
import com.project.custom_product.DTO.PurchaseDTO;
import com.project.custom_product.Service.Service_Impl.Purchase_serviceImpl;
import com.project.custom_product.entities.Purchase;



@RestController
@RequestMapping("/purchases")
public class Purchase_controller {

    @Autowired
    private Purchase_serviceImpl purchase_service;

    
    @Autowired
    private PurchaseMapper mapper;


    // private  PurchaseDTO convertEntityToDto(Purchase purchase){
        

    //     PurchaseDTO purchaseDTO = mapper.convertPurchaseToDto(purchase);
    //     return purchaseDTO;
    // }


    @PostMapping("/customer/{customer_id}/product/{product_id}") // creating an order for a customer 
    public ResponseEntity<PurchaseDTO> create(@RequestBody PurchaseDTO purchase_dto, @PathVariable Integer customer_id, @PathVariable Integer product_id){

        Purchase purchase = mapper.convertPurchaseDTO_ToPurchase(purchase_dto);
        purchase_service.savePurchase(purchase,customer_id,product_id);
        
        return new ResponseEntity<>(purchase_dto,HttpStatus.CREATED);

    }

    @GetMapping("/customer/{customer_id}/product/{product_id}") // showing the order to the customer 
    public ResponseEntity<PurchaseDTO> getPurchase(@PathVariable Integer customer_id, @PathVariable Integer product_id){

        Purchase purchase = purchase_service.findPurchaseBYId(customer_id, product_id);

        PurchaseDTO purchase_dto = mapper.convertPurchaseToDto(purchase);

        return new ResponseEntity<>(purchase_dto,HttpStatus.OK);

    }

    @PutMapping("/update/customer/{customer_id}/product/{product_id}") // updating the number of orders
    public ResponseEntity<PurchaseDTO> update(@RequestBody PurchaseDTO purchase_dto, @PathVariable Integer customer_id, 
           @PathVariable Integer product_id){

            Purchase purchase = mapper.convertPurchaseDTO_ToPurchase(purchase_dto);
            purchase_service.updatePurchase( customer_id, product_id,purchase.getTotal_quantities());
            PurchaseDTO dto = mapper.convertPurchaseToDto(purchase);

            return new ResponseEntity<>(dto,HttpStatus.OK);
    }


    @GetMapping("/totalprice/customer/{customer_id}/product/{product_id}") // showing the bill after ordering

    public ResponseEntity<PurchaseDTO> getBillForPurchase(@PathVariable Integer customer_id, @PathVariable Integer product_id){

        Purchase purchase= purchase_service.getTotalPricePurchase_per_Customer(customer_id,product_id);

        PurchaseDTO dto = mapper.convertPurchaseToDto(purchase);
        

        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @DeleteMapping("/delete/customer/{customer_id}/product/{product_id}")
    
    public ResponseEntity<?> deletePurchase(@PathVariable Integer customer_id, @PathVariable Integer product_id){

        purchase_service.delete(customer_id, product_id);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

    @GetMapping("/all/customer/{customer_id}")
    public ResponseEntity<List<PurchaseDTO>> getAllpurchasesByCustomer(@PathVariable Integer customer_id){
        
        List<Purchase> purchases = purchase_service.getAllCustomersPurchased(customer_id);
        
        
        return new ResponseEntity<> (purchases.stream()
                .map(mapper::convertPurchaseToDto )
                .collect(Collectors.toList()),HttpStatus.OK);
    }


     @GetMapping("/all/product/{product_id}")
    public ResponseEntity<List<PurchaseDTO>> getAllpurchasesByProductId(@PathVariable Integer product_id){
        
        List<Purchase> purchases = purchase_service.getAllProductssPurchased(product_id);
          
        return new ResponseEntity<> (purchases.stream()
                .map(mapper::convertPurchaseToDto )
                .collect(Collectors.toList()),HttpStatus.OK);
    }



     @GetMapping("/all")
    public ResponseEntity<List<PurchaseDTO>> getAllpurchases(){
        

        List<Purchase> purchases = purchase_service.getAllPurchases();
          
        return new ResponseEntity<> (purchases.stream()
                .map(mapper::convertPurchaseToDto )
                .collect(Collectors.toList()),HttpStatus.OK);
       
    }

    

    



    




    
}
