package com.mycompany.lb3;

public class Calculator {
    private static double function(double x) {
        return 1.0 / x;
    }
    
    public static double integral(double a, double b, double n) throws IntegralException {
        // Проверяем корректность шага
        if (n <= 0) {
            throw new IntegralException("Шаг интегрирования должен быть положительным числом!");
        }
        
        // Проверяем, что интервал не нулевой
        if (Math.abs(a - b) < 0.000001) {
            return 0.0; // Интеграл на нулевом интервале равен 0
        }
        
        double sum = 0.0;
        
        try {
            int fullSteps = (int) Math.floor(Math.abs(b - a) / n);
            
            for (int i = 0; i < fullSteps; i++) {
                double x1 = a + i * n;
                double x2 = a + (i + 1) * n;
                sum += (function(x1) + function(x2)) / 2.0 * n;
            }
            
            double remainder = Math.abs(b - a) - fullSteps * n;
            if (remainder > 0.000001) {
                double lastX = a + fullSteps * n;
                sum += (function(lastX) + function(b)) / 2.0 * remainder;
            }
            
            if (b < a) {
                sum = -sum;
            }
            
        } catch (ArithmeticException e) {
            throw new IntegralException("Арифметическая ошибка при вычислении интеграла!", e);
        }
        
        return sum;
    }
}