package com.Ambula.exception;

import net.bytebuddy.implementation.bind.annotation.Super;

public class RessourceNotFoundException extends  RuntimeException{
    public RessourceNotFoundException(String msg){
    super(msg);
    }
}
