package com.exam.demo.problema3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.demo.problema3.model.CuentaBancaria;

public interface CuentaBancariaRepository extends JpaRepository<CuentaBancaria, Long> {

}
