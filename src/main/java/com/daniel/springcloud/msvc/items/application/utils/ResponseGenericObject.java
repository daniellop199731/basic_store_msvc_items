package com.daniel.springcloud.msvc.items.application.utils;

import lombok.Data;

/**
 * Clase genérica para estandarizar las respuestas de los servicios
 * 
 * @param <T> Tipo de objeto que contendrá la respuesta
 */
@Data
public class ResponseGenericObject<T> {
    
    /**
     * Determina si la respuesta fue 
     * exitosa (Se completo correctamente la operacion, con o sin datos) o 
     * no (Hubo alguna validacion de negocio que falló)
     */
    private boolean isSuccessful;

    /**
     * Determina si la respuesta contiene errores
     */
    private boolean withErrors;

    //Mensaje descriptivo breve de la respuesta
    private String message;

    //Objeto de la respuesta, puede ser un objeto simple, una lista, o lo que se requiera
    private T obj;

    public ResponseGenericObject(){}

    public ResponseGenericObject(boolean isSuccessful, String message, T obj){
        this.isSuccessful = isSuccessful;
        this.message = message;
        this.obj = obj;
    }

    public ResponseGenericObject(boolean isSuccessful, boolean withErrors, String message, T obj){
        this.isSuccessful = isSuccessful;
        this.withErrors = withErrors;
        this.message = message;
        this.obj = obj;
    }    

    /**
     * Indica que la respuesta fue exitosa y asigna el mensaje y el objeto de la respuesta
     * @param msg Mensaje descriptivo breve de la respuesta
     * @param obj Objeto de la respuesta, puede ser un objeto simple, una lista, o lo que se requiera
     */
    public void setAsSuccessful(String msg, T obj){
        setSuccessful(true);
        setMessage(msg);
        setObj(obj);
    }

    /**
     * Indica que la respuesta no fue exitosa por fallar alguna validacion de negocio y asigna el mensaje de la respuesta
     * @param msg Mensaje descriptivo breve de la respuesta, puede ser el message de la exception capturada
     */
    public void setAsNotSuccessful(String msg){
        setSuccessful(false);
        setWithErrors(false);
        setMessage(msg);
        setObj(null);
    }

    public void setAsNotSuccessful(String msg, T obj){
        setSuccessful(false);
        setWithErrors(false);
        setMessage(msg);
        setObj(obj);
    }     
    
    /**
     * Indica que la respuesta no fue exitosa debido a alguna excepcion y asigna el mensaje de la respuesta
     * @param msg Mensaje descriptivo breve de la respuesta, puede ser el message de la exception capturada
     */
    public void setAsWithErrors(String msg){
        setSuccessful(false);
        setWithErrors(true);
        setMessage(msg);
        setObj(null);
    }
}
