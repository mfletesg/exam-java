package com.exam.demo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exam.demo.clases.CuentaAhorro;
import com.exam.demo.repository.CuentaBancariaRepository;

@Service // Definimos que la clase va a ser un servicio
public class CuentaAhorroService {

    @Autowired
    private CuentaBancariaRepository cuentaBancariaRepository; // Incorporamos el servicio del empleado

    // @Autowired
    public ResponseEntity<Map<String, Object>> depositar(CuentaAhorro cuentaAhorro) {

        // cuentaBancariaRepository.save(cuentaAhorro);

        return cuentaAhorro.depositar(cuentaAhorro);
    }

}
