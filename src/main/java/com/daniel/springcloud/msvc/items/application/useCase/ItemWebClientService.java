package com.daniel.springcloud.msvc.items.application.useCase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.daniel.springcloud.msvc.items.application.utils.ResponseGenericObject;
import com.daniel.springcloud.msvc.items.domain.model.Item;
import com.daniel.springcloud.msvc.items.domain.model.Product;
import com.daniel.springcloud.msvc.items.domain.port.in.ItemUseCase;

import lombok.RequiredArgsConstructor;

@Primary
@Service
@RequiredArgsConstructor
public class ItemWebClientService implements ItemUseCase {

    private final WebClient.Builder client;

    private List<Item> items;
    private Optional<Item> item;

    private Random random = new Random();    

    private static final String DEFAULT_ERROR_MESSAGE = "Error en la ejecucion del proceso: ";
    private static final String NOT_FOUND_ITEMS = "Items no encontrados";
    private static final String FOUND_ITEMS = "Items encontrados";
    private static final String NOT_FOUND_ITEM = "Item no encontrado";
    private static final String FOUND_ITEM = "Item encontrado";

    @Override
    public ResponseGenericObject<List<Item>> findAll() {
        items = this.client.build()
        .get()
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToFlux(Product.class)
        .map(product -> new Item(product, random.nextInt(10) + 1))
        .collectList()
        .block();

        if (items == null){
            return new ResponseGenericObject<>(
                true
                , NOT_FOUND_ITEMS
                , null
            );
        }        

        if (items.isEmpty()){
            return new ResponseGenericObject<>(
                true
                , NOT_FOUND_ITEMS
                , null
            );
        }

        return new ResponseGenericObject<>(
            true
            , FOUND_ITEMS
            , items
        );
    }

    @Override
    public ResponseGenericObject<Optional<Item>> findById(long id) {
        Map<String, Long> params = new HashMap<>();
        params.put("id", id);
        try{
            item = Optional.ofNullable(
                this.client.build()
                .get()
                .uri("/{id}", params)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Product.class)
                .map(product -> new Item(product, random.nextInt(10) + 1))
                .block()
            );

            if (item.isEmpty()){
                return new ResponseGenericObject<>(
                    true
                    , NOT_FOUND_ITEM
                    , null
                );                
            }            

            if (item.get().getProduct().getId() == null){
                return new ResponseGenericObject<>(
                    true
                    , NOT_FOUND_ITEM
                    , null
                );                
            }

            return new ResponseGenericObject<>(
                true
                , FOUND_ITEM
                , item
            );            

        } catch (HttpMessageNotWritableException ex){
            return new ResponseGenericObject<>(
                false
                , DEFAULT_ERROR_MESSAGE
                , null
            );
        }
    }
    
}
