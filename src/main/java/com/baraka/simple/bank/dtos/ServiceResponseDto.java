package com.baraka.simple.bank.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceResponseDto {
    private String error;
    private Integer code;
}
