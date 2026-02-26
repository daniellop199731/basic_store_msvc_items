package com.daniel.springcloud.msvc.items.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private Product product;
    private Integer quantity;

    public Double getTotal(){
        try{
            return product.getPrice() * quantity;
        } catch (Exception ex){
            return 0.0;
        }
        
    }
    
}
