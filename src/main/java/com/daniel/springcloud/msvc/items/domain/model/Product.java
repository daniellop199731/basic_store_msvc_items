package com.daniel.springcloud.msvc.items.domain.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long id;
    private String name;
    private Double price;
    private Integer stock;
    private Integer port;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createAt;
    
}
