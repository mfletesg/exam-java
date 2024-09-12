package com.exam.demo.problema1.swagger;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.exam.demo.problema1.model.Empleado;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Gestión de Empleados", description = "APIs para la gestión de empleados")
public interface EmpleadoApi {

    @Operation(summary = "Obtener empleados", description = "Retorna una lista de empleados y busca un empleado por su numero de identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list", content = @Content(mediaType = "application/json", schema = @Schema(example = "{\"maxSalary\":[{\"id\":30,\"name\":\"Miguel Fuentes\",\"salary\":10000,\"identificationNumber\":\"222222\"},{\"id\":31,\"name\":\"Juan Perez\",\"salary\":10000,\"identificationNumber\":\"111111\"}],\"averageSalary\":9333.333,\"minSalary\":[{\"id\":32,\"name\":\"Carlos Garcia\",\"salary\":8000,\"identificationNumber\":\"55555\"}],\"employes\":[{\"id\":30,\"name\":\"Miguel Fuentes\",\"salary\":10000,\"identificationNumber\":\"222222\"},{\"id\":31,\"name\":\"Juan Perez\",\"salary\":10000,\"identificationNumber\":\"111111\"},{\"id\":32,\"name\":\"Carlos Garcia\",\"salary\":8000,\"identificationNumber\":\"55555\"}]}"))),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(example = "{\"status\":\"error\",\"message\":\"Empleado no encontrado\"}")))
    })
    ResponseEntity<Map<String, Object>> getPersonById(
            @RequestParam(name = "identificationNumber", required = false) String identificationNumber);

    @Operation(summary = "Crear empleado", description = "Crea un nuevo empleado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empleado creado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(example = "{\"data\":{\"id\":34,\"name\":\"Alan Lopez\",\"salary\":15000,\"identificationNumber\":\"987654\"},\"message\":\"ok\"}"))),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(example = "{\"status\":\"error\",\"message\":\"Empleado no encontrado\"}")))
    })
    public ResponseEntity<Object> createEmploye(@RequestBody Empleado empleado);

    @Operation(summary = "Eliminar empleado", description = "Eliminar empleado por Id")
    public void deletePerson(@PathVariable(name = "id") Long id);

}
