/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pws.c.resfulapi;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pws.c.resfulapi.model.Product;

/**
 *
 * @author Wiratama
 */
@RestController
public class ProductServiceController {
    
    // Method request http get menggunakan hashMap
    private static Map<String, Product> productRepo = new HashMap<>();
   static {
      Product honey = new Product();
      honey.setId("1");
      honey.setName("Honey");
      honey.setQty("1");
      honey.setPrice("Rp. 20000");
      productRepo.put(honey.getId(), honey);
      
      Product almond = new Product();
      almond.setId("2");
      almond.setName("Almond");
      almond.setQty("1");
      almond.setPrice("Rp. 15000");
      productRepo.put(almond.getId(), almond);
      
      Product susu = new Product();
      susu.setId("3");
      susu.setName("MILK");
      susu.setQty("1");
      susu.setPrice("Rp. 10000");
      productRepo.put(susu.getId(), susu);
      
      Product sayur = new Product();
      sayur.setId("4");
      sayur.setName("Sawi");
      sayur.setQty("1");
      sayur.setPrice("Rp. 5000");
      productRepo.put(sayur.getId(), sayur);
      
   }
   
   // Method untuk mendapatkan API product
   @RequestMapping(value = "/products")
   public ResponseEntity<Object> getProduct() {
      return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
   }
   
   // PUT API
   // Method update data product
   @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product){
        
        // Validasi ID Product
        if(!productRepo.containsKey(id)){
            return new ResponseEntity<>("Product Not Found, Please check again", HttpStatus.NOT_FOUND);
        }
        // kondisi ketika berhasil update data 
        else{
            productRepo.remove(id);
            product.setId(id);
            productRepo.put(id, product);
            return new  ResponseEntity<>("Product is updated Successfully",HttpStatus.OK);
        } 
    }
    
    // POST API 
    // Method create data product
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
        
        // Validasi ID product
        if(productRepo.containsKey(product.getId())){
            return new ResponseEntity<>("Failed to post, Product ID cannot be the same ", HttpStatus.OK);
        }
        // kondisi ketika berhasil menambahkan data 
        else{
            productRepo.put(product.getId(), product);
            return new ResponseEntity<>("Product is created Successfully", HttpStatus.CREATED);
        }
    }
    // Delete API product
    // Method menghapus data product
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Object> deleteProduct(@PathVariable("id") String id, @RequestBody Product product) { 
      productRepo.remove(id);
      return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
   }
    
}
