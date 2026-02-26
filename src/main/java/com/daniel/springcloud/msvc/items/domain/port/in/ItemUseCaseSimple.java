package com.daniel.springcloud.msvc.items.domain.port.in;

import java.util.List;
import java.util.Optional;

import com.daniel.springcloud.msvc.items.domain.model.Item;

public interface ItemUseCaseSimple {
    
    List<Item> findAll();
    Optional<Item> findById(long id);

}
