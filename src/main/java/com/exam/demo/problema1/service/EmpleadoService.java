package com.exam.demo.problema1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.demo.problema1.model.Empleado;
import com.exam.demo.problema1.repository.EmpleadoRepository;

@Service // Definimos que la clase va a ser un servicio

public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository; // Añadimos el repositorio de Empleado

    public List<Empleado> getAllEmploye() { // Funcion para obtener los empleados
        return empleadoRepository.findAll();
    }

    public List<Empleado> getEmployeByIdentificationNumber(String in) {
        return empleadoRepository.findByIdentificationNumberLike(in);
    }

    public Empleado createEmploye(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public List<Empleado> findEmployesWithMinSalary() { // Funcion para obtener el salario min
        return empleadoRepository.findEmployesWithMinSalary();
    }

    public List<Empleado> findEmployesWithMaxSalary() { // Funcion para obtener el salario max
        return empleadoRepository.findEmployesWithMaxSalary();
    }

    public void deletePerson(Long id) {
        empleadoRepository.deleteById(id);
    }

    public Optional<Empleado> getEmpleyeByIdentificationNumber(Integer identificartionNumber) {
        return empleadoRepository.findByIdentificationNumber(identificartionNumber);
    }

}
