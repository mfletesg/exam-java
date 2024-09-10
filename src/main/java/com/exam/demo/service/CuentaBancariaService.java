package com.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.demo.model.CuentaBancaria;
import com.exam.demo.repository.CuentaBancariaRepository;

@Service
public class CuentaBancariaService {

    @Autowired
    private CuentaBancariaRepository cuentaBancariaRepository; // Añadimos el repositorio de Cuenta Bancaria

    public CuentaBancaria createCuentaBancaria(CuentaBancaria cuentaBancaria) {
        return cuentaBancariaRepository.save(cuentaBancaria);
    }
}
