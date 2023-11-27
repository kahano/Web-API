package com.project.custom_product.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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

import com.project.custom_product.DTO.productDTO;
import com.project.custom_product.Service.Service_Impl.Product_serviceImpl;

import com.project.custom_product.entities.Product;

@RestController
@RequestMapping("/products")
public class productController {

    @Autowired
    private Product_serviceImpl product_service;

    @Autowired
    private ModelMapper mapper;

     private productDTO convertEntityToDto(Product prod){
        
        
         mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);

        productDTO productDTO = mapper.map(prod,productDTO.class);
        return productDTO;
    }


   



  
    @PostMapping
    public ResponseEntity<productDTO> create (@RequestBody productDTO prodDTO){

        
        Product product = mapper.map(prodDTO,Product.class);

        product_service.saveProduct(product);

   

        return new ResponseEntity<>(prodDTO,HttpStatus.CREATED);
    }

    @GetMapping("/{product_id}")

    public ResponseEntity<productDTO> getCustommer(@PathVariable Integer product_id){


        Product product = product_service.findProductById(product_id);
        productDTO productDTO = mapper.map(product,productDTO.class);
        return new ResponseEntity<>(productDTO,HttpStatus.OK);
        

    }

    
    @GetMapping

    public ResponseEntity<List<productDTO>> findAllproducts(){
        
        List<Product> customers = product_service.getProducts();
          
        return new ResponseEntity<> (customers.stream()
                .map(this::convertEntityToDto )
                .collect(Collectors.toList()),HttpStatus.OK);

     
    }

    @PutMapping("/{product_id}")
    public ResponseEntity<productDTO> update(@PathVariable Integer product_id, @RequestBody productDTO product_dto){


          Product product = mapper.map(product_dto,Product.class);
       
         product_service.updateProduct(product_id, product);

        
        productDTO product_DTO = convertEntityToDto(product);
        return new ResponseEntity<>(product_DTO,HttpStatus.OK);

  

        
    }

    @DeleteMapping("/{product_id}")
    public ResponseEntity<?> delete_customer(@PathVariable Integer product_id){
        product_service.deleteProductbyId(product_id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }




    
}
