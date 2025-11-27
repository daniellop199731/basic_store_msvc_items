package com.daniel.springcloud.msvc.items.application.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase genérica para estandarizar las respuestas de los servicios
 * 
 * @param <T> Tipo de objeto que contendrá la respuesta
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ResponseObject {
    
    /**
     * Determina si la respuesta fue 
     * exitosa (Se completo correctamente la operacion) o 
     * no (Se producjo un error al realizar la operacion o no se encontro informacion)
     */
    private boolean isSuccessful;

    //Mensaje descriptivo breve de la respuesta
    private String message;

    //Objeto de la respuesta, puede ser un objeto simple, una lista, o lo que se requiera
    private Object obj;

    /**
     * Indica que la respuesta fue exitosa y asigna el mensaje y el objeto de la respuesta
     * @param msg Mensaje descriptivo breve de la respuesta
     * @param obj Objeto de la respuesta, puede ser un objeto simple, una lista, o lo que se requiera
     */
    public void setAsSuccessful(String msg, Object obj){
        setSuccessful(true);
        setMessage(msg);
        setObj(obj);
    }

    /**
     * Indica que la respuesta no fue exitosa y asiga el mensaje de la respuesta
     * @param msg Mensaje descriptivo breve de la respuesta, puede ser el message de la exception capturada
     */
    public void setAsNotSuccessful(String msg){
        setSuccessful(false);
        setMessage(msg);
        setObj(null);
    }    

    public ResponseGenericObject<?> migration (ResponseGenericObject<?> rObjDestination) {
        rObjDestination.setSuccessful(this.isSuccessful());
        rObjDestination.setMessage(this.getMessage());
        return rObjDestination;
    }
}
