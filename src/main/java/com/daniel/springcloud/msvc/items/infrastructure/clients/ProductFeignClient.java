package com.daniel.springcloud.msvc.items.infrastructure.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.daniel.springcloud.msvc.items.application.utils.ResponseGenericObject;
import com.daniel.springcloud.msvc.items.domain.model.Product;

@FeignClient(url = "localhost:8081/api/products", name = "msvc-products")
public interface ProductFeignClient {

    @GetMapping("")
    ResponseEntity<ResponseGenericObject<List<Product>>> getAllProducts() throws Exception;

    @GetMapping("/{id}")
    ResponseEntity<ResponseGenericObject<Product>> getProductById(@PathVariable("id") Long id) throws Exception;    
    
}
