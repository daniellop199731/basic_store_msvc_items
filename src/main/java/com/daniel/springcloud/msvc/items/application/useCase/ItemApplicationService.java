package com.daniel.springcloud.msvc.items.application.useCase;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.daniel.springcloud.msvc.items.application.utils.ResponseGenericObject;
import com.daniel.springcloud.msvc.items.domain.model.Item;
import com.daniel.springcloud.msvc.items.domain.model.Product;
import com.daniel.springcloud.msvc.items.domain.port.in.ItemUseCase;
import com.daniel.springcloud.msvc.items.infrastructure.clients.ProductFeignClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemApplicationService implements ItemUseCase{

    private final ProductFeignClient client;

    private ResponseGenericObject<List<Product>> responseObjList = new ResponseGenericObject<>();
    private ResponseGenericObject<Product> responseObj = new ResponseGenericObject<>();
    private Random random = new Random();

    private static final String DEFAULT_ERROR_MESSAGE = "Error en la ejecucion del proceso: ";
    private static final String NOT_FOUND_ITEMS = "Items no encontrados";
    private static final String FOUND_ITEMS = "Items encontrados";
    private static final String NOT_FOUND_ITEM = "Item no encontrado";
    private static final String FOUND_ITEM = "Item encontrado";

    @Override
    public ResponseGenericObject<List<Item>> findAll() {
        try{
            responseObjList = client.getAllProducts().getBody();
            if(responseObjList == null){
                return new ResponseGenericObject<>(
                    true,
                    NOT_FOUND_ITEMS,
                    null
                );
            }
            return new ResponseGenericObject<>(
                true,
                FOUND_ITEMS,
                responseObjList.getObj()
                    .stream()
                    .map(product -> new Item(product, random.nextInt(10) + 1))
                    .toList()
            );
        } catch(Exception e){
            return new ResponseGenericObject<>(
                false,
                true,
                DEFAULT_ERROR_MESSAGE + e.getMessage(),
                null
            );
        }
    }

    @Override
    public ResponseGenericObject<Optional<Item>> findById(long id) {
        try{
            responseObj = client.getProductById(id).getBody();
            if(responseObj == null || !responseObj.isSuccessful()){
                return new ResponseGenericObject<>(
                    false,
                    NOT_FOUND_ITEM,
                    null
                );                
            }
            return new ResponseGenericObject<>(
                true,
                FOUND_ITEM,
                Optional.ofNullable(new Item(responseObj.getObj(), random.nextInt(10) + 1))
            );
        } catch(Exception e){
            return new ResponseGenericObject<>(
                false,
                true,
                DEFAULT_ERROR_MESSAGE + e.getMessage(),
                null
            );            
        }
    }
    
}
