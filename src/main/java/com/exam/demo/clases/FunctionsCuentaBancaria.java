package com.exam.demo.clases;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.exam.demo.service.CuentaBancariaService;

@Component
public class FunctionsCuentaBancaria {

    @Autowired
    private CuentaBancariaService cuentaBancariaService;

    public ResponseEntity<Map<String, Object>> ValidateDeposito(Map<String, Object> datos) {
        Map<String, Object> response = new HashMap<>();

        String numeroCuenta = (String) datos.get("numeroCuenta");
        Double saldo = ((Number) datos.get("saldo")).doubleValue();

        if (saldo <= 0) {
            response.put("message", "El saldo a depositar debe ser mayor que 0.");
            response.put("data", datos);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        List<Map<String, Object>> datosCuenta = cuentaBancariaService.getCuentaBancaria(numeroCuenta);

        if (datosCuenta.isEmpty() || datosCuenta == null) {
            response.put("message",
                    "No se puede depositar ya que la cuenta con numero: " + numeroCuenta + " no existe");
            response.put("data", datos);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Map<String, Object> primeraCuenta = datosCuenta.get(0);
        Double saldoActual = (Double) primeraCuenta.get("saldo");

        saldoActual = saldo + saldoActual;

        cuentaBancariaService.updateCuentaBancaria(numeroCuenta, saldoActual);

        response.put("message", "Deposito realizado");
        response.put("data", datos);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Map<String, Object>> ValidateRetiro(Map<String, Object> datos) {
        Map<String, Object> response = new HashMap<>();

        String numeroCuenta = (String) datos.get("numeroCuenta");
        Double saldo = ((Number) datos.get("saldo")).doubleValue();

        if (saldo <= 0) {
            response.put("message", "El saldo a depositar debe ser mayor que 0.");
            response.put("data", datos);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        List<Map<String, Object>> datosCuenta = cuentaBancariaService.getCuentaBancaria(numeroCuenta);

        if (datosCuenta.isEmpty() || datosCuenta == null) {
            response.put("message",
                    "No se puede retirar ya que la cuenta con numero: " + numeroCuenta + " no existe");
            response.put("data", datos);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Map<String, Object> primeraCuenta = datosCuenta.get(0);
        Double saldoActual = (Double) primeraCuenta.get("saldo");

        if (saldoActual < saldo) {
            response.put("message",
                    "No se puede retirar ya que el saldo actual (" + saldoActual + ") es inferior al saldo solicitado ("
                            + saldo + ")");
            response.put("data", datos);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        saldoActual = saldoActual - saldo;

        cuentaBancariaService.updateCuentaBancaria(numeroCuenta, saldoActual);

        response.put("message", "Retiro realizado");
        response.put("data", datos);

        return ResponseEntity.ok(response);
    }
}
