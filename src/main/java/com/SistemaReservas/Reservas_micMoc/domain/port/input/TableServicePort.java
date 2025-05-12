package com.SistemaReservas.Reservas_micMoc.domain.port.input;

import com.SistemaReservas.Reservas_micMoc.domain.model.Table;

public interface TableServicePort {

    Table createTable(int capacity);
}
