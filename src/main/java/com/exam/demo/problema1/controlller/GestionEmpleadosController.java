package com.exam.demo.problema1.controlller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.exam.demo.problema1.model.Empleado;
import com.exam.demo.problema1.service.EmpleadoService;
import com.exam.demo.problema1.swagger.EmpleadoApi;

@RestController
// @CrossOrigin(origins = "*", methods = { RequestMethod.GET,
// RequestMethod.POST, RequestMethod.DELETE })
@RequestMapping("/empleado")

public class GestionEmpleadosController implements EmpleadoApi {

    @Autowired
    private EmpleadoService empleadoService; // Incorporamos el servicio del empleado

    @GetMapping
    public ResponseEntity<Map<String, Object>> getPersonById(
            @RequestParam(name = "identificationNumber", required = false) String identificationNumber) {
        try {
            String in = String.valueOf(identificationNumber);
            List<Empleado> employes = null;

            if (identificationNumber == null) {
                employes = empleadoService.getAllEmploye();
            } else {
                employes = empleadoService.getEmployeByIdentificationNumber(in);
            }

            // Obtenermos el promedio del salario de los trabajadores
            int countEmployes = 0;
            float salaryEmployes = 0;
            float averageSalary = 0;
            List<Empleado> allEmployes = empleadoService.getAllEmploye();
            for (Empleado e : allEmployes) {
                countEmployes++;
                salaryEmployes = (salaryEmployes + e.getSalary());
            }

            if (countEmployes > 0) {
                averageSalary = salaryEmployes / countEmployes;
            } else {
                averageSalary = 0;
            }

            List<Empleado> minSalary = empleadoService.findEmployesWithMinSalary();
            List<Empleado> maxSalary = empleadoService.findEmployesWithMaxSalary();

            Map<String, Object> data = new HashMap<>();
            data.put("employes", employes);
            data.put("averageSalary", averageSalary);
            data.put("minSalary", minSalary);
            data.put("maxSalary", maxSalary);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "ok");
            response.put("data", data);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<Object> createEmploye(@RequestBody Empleado empleado) {
        try {

            Optional<Empleado> e = empleadoService.getEmpleyeByIdentificationNumber(empleado.getIdentificationNumber());

            Map<String, Object> response = new HashMap<>();

            if (e.isPresent()) {
                response.put("message", "El numero de identificador ya existe en el sistema");
                response.put("data", empleado);
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }

            Empleado createdEmpleado = empleadoService.createEmploye(empleado);

            response.put("message", "ok");
            response.put("data", createdEmpleado);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable(name = "id") Long id) {
        empleadoService.deletePerson(id);
    }

}
