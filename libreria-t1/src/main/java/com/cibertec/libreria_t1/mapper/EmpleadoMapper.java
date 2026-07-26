package com.cibertec.libreria_t1.mapper;

import com.cibertec.libreria_t1.dto.EmpleadoRequest;
import com.cibertec.libreria_t1.dto.EmpleadoResponse;
import com.cibertec.libreria_t1.model.Empleado;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoMapper {

    public Empleado toEntity(EmpleadoRequest request) {
        return new Empleado(
                request.getNombres(),
                request.getApellidos(),
                request.getNumeroDocumento(),
                request.getTelefono(),
                request.getCargo(),
                request.getFechaIngreso()
        );
    }

    public EmpleadoResponse toResponse(Empleado empleado) {
        return new EmpleadoResponse(
                empleado.getId(),
                empleado.getNombres(),
                empleado.getApellidos(),
                empleado.getNumeroDocumento(),
                empleado.getTelefono(),
                empleado.getCargo(),
                empleado.getFechaIngreso()
        );
    }
}
