package com.daniel.springcloud.msvc.items.infrastructure.adapter.in.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.daniel.springcloud.msvc.items.application.utils.ResponseGenericObject;
import com.daniel.springcloud.msvc.items.domain.model.Item;
import com.daniel.springcloud.msvc.items.domain.port.in.ItemUseCase;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemUseCase useCase;

    @GetMapping("")
    public ResponseEntity<ResponseGenericObject<List<Item>>> getAllItems() {
        return ResponseEntity.ok().body(useCase.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseGenericObject<Optional<Item>>> getItemById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(useCase.findById(id));
    }
    
    
    
}
