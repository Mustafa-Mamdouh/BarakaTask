package com.baraka.simple.bank.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersistenceLayerException extends RuntimeException{
    public PersistenceLayerException(String message) {
        super(message);
    }
}
