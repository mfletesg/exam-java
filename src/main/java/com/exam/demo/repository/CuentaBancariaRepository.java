package com.exam.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.demo.model.CuentaBancaria;

public interface CuentaBancariaRepository extends JpaRepository<CuentaBancaria, Long> {

}
