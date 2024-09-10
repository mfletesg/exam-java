package com.exam.demo.model;

import com.exam.demo.clases.FiguraGeometrica;

public class Triangulo extends FiguraGeometrica {

    private double lado1;
    private double lado2;
    private double lado3;

    public Triangulo(double lado1, double lado2, double lado3) {
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.lado3 = lado3;

    }

    @Override
    public double calcularArea() {
        // Calcular el semiperímetro
        double s = (lado1 + lado2 + lado3) / 2;
        // Calcular el área usando la fórmula de Herón
        double area = Math.sqrt(s * (s - lado1) * (s - lado2) * (s - lado3));
        return area;
    }

    @Override
    public double calcularPerimetro() {
        return (lado1 + lado2 + lado3);
    }
}
