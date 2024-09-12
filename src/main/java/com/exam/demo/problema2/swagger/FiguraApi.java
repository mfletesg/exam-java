package com.exam.demo.problema2.swagger;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Figuras Geometicas", description = "APIs para obtener el area y perimetro del circulo, triangulo y rectangulo")
public interface FiguraApi {

    @Operation(summary = "Calcular Cirulo", description = "Calcula el area y perimetro de un circulo por su radio")
    public ResponseEntity<Map<String, Object>> calcularCirculo(
            @RequestParam(name = "radio", required = true) double radio);

    @Operation(summary = "Calcular Rectangulo", description = "Calcula el area y perimetro de un rectangulo por su largo y ancho")
    public ResponseEntity<Map<String, Object>> calcularRectangulo(
            @RequestParam(name = "largo", required = true) double largo,
            @RequestParam(name = "ancho", required = true) double ancho);

    @Operation(summary = "Calcular Rectangulo", description = "Calcula el area y perimetro de un triangulo por sus tres lados")
    public ResponseEntity<Map<String, Object>> calcularTriangulo(
            @RequestParam(name = "lado1", required = true) double lado1,
            @RequestParam(name = "lado2", required = true) double lado2,
            @RequestParam(name = "lado3", required = true) double lado3);
}
