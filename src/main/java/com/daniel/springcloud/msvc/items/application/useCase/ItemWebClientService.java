package com.daniel.springcloud.msvc.items.application.useCase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.daniel.springcloud.msvc.items.domain.model.Item;
import com.daniel.springcloud.msvc.items.domain.model.Product;
import com.daniel.springcloud.msvc.items.domain.port.in.ItemUseCaseSimple;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemWebClientService implements ItemUseCaseSimple {

    private final WebClient.Builder client;

    private List<Product> products;
    private Product product;

    private Random random = new Random();    

    private static final String DEFAULT_ERROR_MESSAGE = "Error en la ejecucion del proceso: ";
    private static final String NOT_FOUND_ITEMS = "Items no encontrados";
    private static final String FOUND_ITEMS = "Items encontrados";
    private static final String NOT_FOUND_ITEM = "Item no encontrado";
    private static final String FOUND_ITEM = "Item encontrado";

    @Override
    public List<Item> findAll() {
        return this.client.build()
        .get()
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToFlux(Product.class)
        .map(productAux -> new Item(productAux, random.nextInt(10) + 1))
        .collectList()
        .block();
    }

    @Override
    public Optional<Item> findById(long id) {
        Map<String, Long> params = new HashMap<>();
        params.put("id", id);
        try{
            return Optional.ofNullable(
                this.client.build()
                .get()
                .uri("/{id}", params)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Product.class)
                .map(productAux -> new Item(productAux, random.nextInt(10) + 1))
                .block()
            );
        } catch (HttpMessageNotWritableException ex){
            return Optional.empty();
        }
    }
    
}
