package com.exam.demo.problema3.swagger;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.exam.demo.problema3.model.CuentaBancaria;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cuenta Bancaria", description = "APIs gestionar las cuentas bancarias de una empresa")
public interface CuentaBancariaApi {

    @Operation(summary = "Depositar monto a una cuenta", description = "Deposita un monto a una cuenta por su numero de cuenta")
    public ResponseEntity<Map<String, Object>> depositar(@RequestBody Map<String, Object> requestBody);

    @Operation(summary = "Retira monto a una cuenta", description = "Retira un monto a una cuenta por su numero de cuenta")
    public ResponseEntity<Map<String, Object>> retirar(@RequestBody Map<String, Object> requestBody);

    @Operation(summary = "Obtiene las cuentas", description = "Obtiene un listado de todas las cuentas")
    public ResponseEntity<Map<String, Object>> get();

    @Operation(summary = "Crear cuenta", description = "Crear nueva cuenta bancaria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Crea una nueva cuenta", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CuentaBancaria.class))),

    })
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> requestBody);
}
