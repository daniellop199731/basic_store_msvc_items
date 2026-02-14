package com.daniel.springcloud.msvc.items.infrastructure.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.daniel.springcloud.msvc.items.domain.model.Product;

//Interface que usa FeignClient para comunicarce con otro microservicio ubicado en la propiedad url 
//y de nombre msvc-products (propiedad que se ubica en el archivo application.properties, spring.application.name)
@FeignClient(url = "localhost:8081/api/products", name = "msvc-products")
public interface ProductFeignClient {

    @GetMapping("")
    ResponseEntity<List<Product>> getAllProducts() throws Exception;

    @GetMapping("/{id}")
    ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws Exception;    
    
}
