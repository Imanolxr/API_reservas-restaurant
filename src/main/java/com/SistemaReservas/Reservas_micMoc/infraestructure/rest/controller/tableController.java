package com.SistemaReservas.Reservas_micMoc.infraestructure.rest.controller;

import com.SistemaReservas.Reservas_micMoc.application.service.TableService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/table")
public class tableController {
    private final TableService tableService;

    public tableController(TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping("/new/{capacity}")
    public ResponseEntity<Void> newTable(@PathVariable int capacity){
        tableService.createTable(capacity);
        return ResponseEntity.ok().build();

    }
}
