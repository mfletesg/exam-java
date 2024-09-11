package com.exam.demo.problema3.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.exam.demo.problema3.model.CuentaBancaria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class CuentaBancariaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public List<Map<String, Object>> getCuentaBancaria(String numeroCuenta) {
        String sql = "SELECT numero_cuenta, saldo, tipo_cuenta FROM cuenta_bancaria WHERE numero_cuenta = ?";
        return jdbcTemplate.queryForList(sql, numeroCuenta);
    }

    @Transactional
    public CuentaBancaria createCuentaBancaria(String numeroCuenta, Double saldo, String tipoCuenta) {
        String sql = "INSERT INTO cuenta_bancaria (numero_cuenta, saldo, tipo_cuenta) VALUES (?, ?, ?)";
        entityManager.createNativeQuery(sql)
                .setParameter(1, numeroCuenta)
                .setParameter(2, saldo)
                .setParameter(3, tipoCuenta)
                .executeUpdate();
        return null;
    }

    @Transactional
    public void updateCuentaBancaria(String numeroCuenta, Double saldo) {
        String sql = "UPDATE cuenta_bancaria SET saldo = ? WHERE numero_cuenta = ?";
        entityManager.createNativeQuery(sql)
                .setParameter(1, saldo)
                .setParameter(2, numeroCuenta)
                .executeUpdate();
    }

    @Transactional
    public List<Map<String, Object>> getCuentasBancarias() {
        String sql = "SELECT id, numero_cuenta, saldo, tipo_cuenta FROM cuenta_bancaria";
        return jdbcTemplate.queryForList(sql);
    }

}
