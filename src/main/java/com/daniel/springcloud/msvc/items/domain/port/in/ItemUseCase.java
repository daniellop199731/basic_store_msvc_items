package com.daniel.springcloud.msvc.items.domain.port.in;

import java.util.List;
import java.util.Optional;

import com.daniel.springcloud.msvc.items.application.utils.ResponseGenericObject;
import com.daniel.springcloud.msvc.items.domain.model.Item;

public interface ItemUseCase {
    
    ResponseGenericObject<List<Item>> findAll();
    ResponseGenericObject<Optional<Item>> findById(long id);

}
