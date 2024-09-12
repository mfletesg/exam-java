package com.exam.demo.problema3.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.exam.demo.problema3.clases.CuentaAhorro;
import com.exam.demo.problema3.clases.CuentaCorriente;
import com.exam.demo.problema3.service.CuentaBancariaService;
import com.exam.demo.problema3.swagger.CuentaBancariaApi;

@RestController
public class CuentaAhorroController implements CuentaBancariaApi {

    @Autowired
    private CuentaAhorro cuentaAhorroService;

    @Autowired
    private CuentaCorriente cuentaCorriente;

    @Autowired
    private CuentaBancariaService cuentaBancariaService;

    @PutMapping("/cuenta/depositar")
    public ResponseEntity<Map<String, Object>> depositar(@RequestBody Map<String, Object> requestBody) {
        Map<String, Object> response = new HashMap<>();

        String numeroCuenta = (String) requestBody.get("numeroCuenta");
        List<Map<String, Object>> datosCuenta = cuentaBancariaService.getCuentaBancaria(numeroCuenta);

        if (datosCuenta.isEmpty() || datosCuenta == null) {
            response.put("message",
                    "No se puede realizar la acción ya que la cuenta con numero: " + numeroCuenta + " no existe");
            response.put("data", requestBody);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Map<String, Object> cuenta = datosCuenta.get(0);
        String tipo_cuenta = (String) cuenta.get("tipo_cuenta");

        System.out.println(tipo_cuenta);

        switch (tipo_cuenta) {
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
        String numeroCuenta = (String) requestBody.get("numeroCuenta");
        List<Map<String, Object>> datosCuenta = cuentaBancariaService.getCuentaBancaria(numeroCuenta);

        if (datosCuenta.isEmpty() || datosCuenta == null) {
            response.put("message",
                    "No se puede realizar la acción ya que la cuenta con numero: " + numeroCuenta + " no existe");
            response.put("data", requestBody);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Map<String, Object> cuenta = datosCuenta.get(0);
        String tipo_cuenta = (String) cuenta.get("tipo_cuenta");

        System.out.println(tipo_cuenta);

        switch (tipo_cuenta) {
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

    @PostMapping("/cuenta")
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
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
