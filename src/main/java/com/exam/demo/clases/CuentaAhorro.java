package com.exam.demo.clases;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exam.demo.model.CuentaBancaria;

import jakarta.transaction.Transactional;

@Service
public class CuentaAhorro extends CuentaBancaria {

    @Autowired
    private FunctionsCuentaBancaria functionsCuentaBancaria;

    @Transactional
    public ResponseEntity<Map<String, Object>> depositar(Map<String, Object> datos) {
        return functionsCuentaBancaria.ValidateDeposito(datos);
    }

    @Override
    public ResponseEntity<Map<String, Object>> retirar(Map<String, Object> datos) {
        return functionsCuentaBancaria.ValidateRetiro(datos);
    }

}
