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
import com.project.custom_product.MapperDTO.ProductMapper;
import com.project.custom_product.Service.Service_Impl.Product_serviceImpl;

import com.project.custom_product.entities.Product;

@RestController
@RequestMapping("/products")
public class productController {

    @Autowired
    private Product_serviceImpl product_service;

    @Autowired
    private ProductMapper mapper;


   
  
    @PostMapping
    public ResponseEntity<productDTO> create (@RequestBody productDTO prodDTO){

        
        Product product = mapper.converProducDto_TOProduct(prodDTO);

        product_service.saveProduct(product);

   

        return new ResponseEntity<>(prodDTO,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")

    public ResponseEntity<productDTO> getProduct(@PathVariable Integer id){


        Product product = product_service.findProductById(id);
        productDTO productDTO = mapper.convertProductToDto(product);
        return new ResponseEntity<>(productDTO,HttpStatus.OK);
        

    }

    
    @GetMapping

    public ResponseEntity<List<productDTO>> findAllproducts(){
        
        List<Product> customers = product_service.getProducts();
          
        return new ResponseEntity<> (customers.stream()
                .map(mapper::convertProductToDto )
                .collect(Collectors.toList()),HttpStatus.OK);

     
    }

    @PutMapping("/{id}")
    public ResponseEntity<productDTO> updateProduct(@PathVariable Integer id, @RequestBody productDTO product_dto){


          Product product = mapper.converProducDto_TOProduct(product_dto);
       
         product_service.updateProduct( id, product);

        
        productDTO product_DTO = mapper.convertProductToDto(product);
        return new ResponseEntity<>(product_DTO,HttpStatus.OK);

  

        
    }

    @DeleteMapping("/{product_id}")
    public ResponseEntity<?> delete_Product(@PathVariable Integer product_id){
        product_service.deleteProductbyId(product_id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }




    
}
