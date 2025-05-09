package com.SistemaReservas.Reservas_micMoc.infraestructure.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientSuggestionDTO {
    private Long id;
    private String name;
    private String lastName;
    private String phone;
    private String email;
}
