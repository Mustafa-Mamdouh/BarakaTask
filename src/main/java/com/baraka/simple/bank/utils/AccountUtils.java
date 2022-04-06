package com.baraka.simple.bank.utils;

import com.baraka.simple.bank.exceptions.ApplicationException;
import org.springframework.http.HttpStatus;

public class AccountUtils {
    public static ApplicationException notFound() {
       return new ApplicationException(HttpStatus.BAD_REQUEST.value(), "Account not found!");
    }
}
