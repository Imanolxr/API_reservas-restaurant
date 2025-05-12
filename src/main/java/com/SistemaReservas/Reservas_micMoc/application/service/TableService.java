package com.SistemaReservas.Reservas_micMoc.application.service;


import com.SistemaReservas.Reservas_micMoc.domain.model.Table;
import com.SistemaReservas.Reservas_micMoc.domain.port.input.TableServicePort;
import com.SistemaReservas.Reservas_micMoc.domain.port.output.TableRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class TableService implements TableServicePort {
    private final TableRepositoryPort tableRepo;

    public TableService(TableRepositoryPort tableRepo) {
        this.tableRepo = tableRepo;
    }


    @Override
    public Table createTable(int capacity) {

        Long nextNumber;
        if (tableRepo.countTables() == null){
             nextNumber = 1L;
        }else{
            nextNumber = tableRepo.countTables() + 1;
        }
        String tableNumber = "Mesa - " + nextNumber;
        Table table = new Table(tableNumber, capacity);

        return tableRepo.saveNewTable(table);

    }
}
