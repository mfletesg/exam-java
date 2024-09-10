package com.exam.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exam.demo.model.Circulo;
import com.exam.demo.model.Rectangulo;
import com.exam.demo.model.Triangulo;

@RestController
public class FiguraController {
    @GetMapping("/circulo")
    public ResponseEntity<Map<String, Object>> calcularCirculo(
            @RequestParam(name = "radio", required = true) double radio) {
        System.out.println(radio);
        Circulo circulo = new Circulo(radio);

        Map<String, Object> data = new HashMap<>();
        data.put("area", circulo.calcularArea());
        data.put("perimetro", circulo.calcularPerimetro());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "ok");
        response.put("data", data);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/rectangulo")
    public ResponseEntity<Map<String, Object>> calcularRectangulo(
            @RequestParam(name = "largo", required = true) double largo,
            @RequestParam(name = "ancho", required = true) double ancho) {
        Rectangulo rectangulo = new Rectangulo(largo, ancho);

        Map<String, Object> data = new HashMap<>();
        data.put("area", rectangulo.calcularArea());
        data.put("perimetro", rectangulo.calcularPerimetro());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "ok");
        response.put("data", data);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/triangulo")
    public ResponseEntity<Map<String, Object>> calcularTriangulo(
            @RequestParam(name = "lado1", required = true) double lado1,
            @RequestParam(name = "lado2", required = true) double lado2,
            @RequestParam(name = "lado3", required = true) double lado3) {

        Triangulo triangulo = new Triangulo(lado1, lado2, lado3);

        Map<String, Object> data = new HashMap<>();
        data.put("area", triangulo.calcularArea());
        data.put("perimetro", triangulo.calcularPerimetro());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "ok");
        response.put("data", data);
        return ResponseEntity.ok(response);
    }
}