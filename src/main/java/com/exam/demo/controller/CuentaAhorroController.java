package com.exam.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exam.demo.clases.CuentaAhorro;
import com.exam.demo.clases.CuentaCorriente;
import com.exam.demo.model.CuentaBancaria;
import com.exam.demo.service.CuentaBancariaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class CuentaAhorroController {

    @Autowired
    private CuentaAhorro cuentaAhorroService;

    @Autowired
    private CuentaCorriente cuentaCorriente;

    @Autowired
    private CuentaBancariaService cuentaBancariaService;

    @PutMapping("/cuenta/depositar")
    public ResponseEntity<Map<String, Object>> depositar(@RequestBody Map<String, Object> requestBody) {
        Map<String, Object> response = new HashMap<>();
        if (requestBody == null || !requestBody.containsKey("tipoCuenta") || requestBody.get("tipoCuenta") == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        String tipoCuenta = (String) requestBody.get("tipoCuenta");
        switch (tipoCuenta) {
            case "Cuenta Ahorro":
                return cuentaAhorroService.depositar(requestBody);
            case "Cuenta Corriente":
                return cuentaCorriente.depositar(requestBody);
            default:
                response.put("message", "Error de datos");
                response.put("data", requestBody);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/cuenta/retirar")
    public ResponseEntity<Map<String, Object>> retirar(@RequestBody Map<String, Object> requestBody) {
        Map<String, Object> response = new HashMap<>();
        if (requestBody == null || !requestBody.containsKey("tipoCuenta") || requestBody.get("tipoCuenta") == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        String tipoCuenta = (String) requestBody.get("tipoCuenta");

        switch (tipoCuenta) {
            case "Cuenta Ahorro":
                return cuentaAhorroService.retirar(requestBody);
            case "Cuenta Corriente":
                return cuentaCorriente.retirar(requestBody);
            default:
                response.put("message", "Error de datos");
                response.put("data", requestBody);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/cuenta")
    public ResponseEntity<Map<String, Object>> get() {
        Map<String, Object> response = new HashMap<>();

        List<Map<String, Object>> result = cuentaBancariaService.getCuentasBancarias();

        response.put("message", "ok");
        response.put("data", result);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Depostidar", description = "Depositar a cuenta bancaria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Depositar a cuenta", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CuentaBancaria.class))),

    })
    @PostMapping("/cuenta/cuentaAhorro")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> requestBody) {
        String numeroCuenta = (String) requestBody.get("numeroCuenta");
        Double saldo = ((Number) requestBody.get("saldo")).doubleValue();
        String tipoCuenta = (String) requestBody.get("tipoCuenta");

        List<Map<String, Object>> result = cuentaBancariaService.getCuentaBancaria(numeroCuenta);

        Map<String, Object> response = new HashMap<>();

        if (!result.isEmpty()) {
            response.put("message",
                    "La cuenta ya ha sido creada anteriormente con el numero de cuenta " + numeroCuenta);
            response.put("data", result);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        cuentaBancariaService.createCuentaBancaria(numeroCuenta, saldo, tipoCuenta);

        response.put("message", "ok");
        response.put("data", requestBody);
        return ResponseEntity.ok(response);
    }

}
