package com.exam.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exam.demo.clases.CuentaAhorro;
import com.exam.demo.model.CuentaBancaria;
import com.exam.demo.service.CuentaAhorroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class CuentaAhorroController {

    @Autowired
    private CuentaAhorroService cuentaAhorroService;

    @Operation(summary = "Depostidar", description = "Depositar a cuenta bancaria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Depositar a cuenta", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CuentaBancaria.class))),

    })
    @PostMapping("/cuenta/cuentaAhorro/depositar")
    public ResponseEntity<Map<String, Object>> depositar(@RequestBody CuentaAhorro cuentaAhorro) {
        return cuentaAhorroService.depositar(cuentaAhorro);
    }

    @PostMapping("/cuenta/cuentaAhorro/retirar")
    public ResponseEntity<Map<String, Object>> retirar(@RequestParam(name = "monto", required = true) double monto) {

        Map<String, Object> response = new HashMap<>();
        response.put("message", "ok");
        response.put("data", "ok");
        return ResponseEntity.ok(response);
    }

}
