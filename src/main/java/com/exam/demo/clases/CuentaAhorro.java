package com.exam.demo.clases;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exam.demo.model.CuentaBancaria;
import com.exam.demo.repository.CuentaBancariaRepository;

@Service
public class CuentaAhorro extends CuentaBancaria {

    @Autowired
    private CuentaBancariaRepository cuentaBancariaRepository; // Añadimos el repositorio de Cuenta Bancaria

    @Override
    public ResponseEntity<Map<String, Object>> depositar(CuentaBancaria cuentaBancaria) {
        System.out.println("Entroooooo");

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Deposito realizado");
        response.put("data", cuentaBancaria);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> retirar(double monto) {
        // Implementación del método retirar
        return ResponseEntity.ok(Map.of("message", "Retiro realizado", "data", monto));
    }
}
