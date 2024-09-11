package com.exam.demo.problema1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exam.demo.problema1.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    // Usa una consulta JPQL que convierta el número de identificación a String para
    // aplicar el operador LIKE.
    @Query("SELECT e FROM Empleado e WHERE CAST(e.identificationNumber AS string) LIKE %:identificationNumber%")
    List<Empleado> findByIdentificationNumberLike(@Param("identificationNumber") String identificationNumber);

    @Query("SELECT e FROM Empleado e WHERE e.salary = (SELECT MIN(e2.salary) FROM Empleado e2)")
    List<Empleado> findEmployesWithMinSalary();

    @Query("SELECT e FROM Empleado e WHERE e.salary = (SELECT MAX(e2.salary) FROM Empleado e2)")
    List<Empleado> findEmployesWithMaxSalary();

    Optional<Empleado> findByIdentificationNumber(Integer identificationNumber);
}
