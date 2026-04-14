package com.mycompany.lb5;

import java.io.Serializable;

public class RecIntegral implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private double lowerBound;
    private double upperBound;
    private double step;
    private double result;
    
    private static final double MIN_VALUE = 0.000001;
    private static final double MAX_VALUE = 1000000;

    public RecIntegral(double lowerBound, double upperBound, double step, double result) 
            throws IntegralException {
        
        validateValue(lowerBound, "Нижний предел");
        validateValue(upperBound, "Верхний предел");
        validateValue(step, "Шаг");
        
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.step = step;
        this.result = result;
    }
    
    private void validateValue(double value, String fieldName) throws IntegralException {
        if (value < MIN_VALUE || value > MAX_VALUE) {
            throw new IntegralException(
                String.format("%s должен быть в диапазоне от %f до %f! (Введено: %f)", 
                fieldName, MIN_VALUE, MAX_VALUE, value)
            );
        }
    }
    
    private static void validateStaticValue(double value, String fieldName) throws IntegralException {
        if (value < MIN_VALUE || value > MAX_VALUE) {
            throw new IntegralException(
                String.format("%s должен быть в диапазоне от %f до %f! (Введено: %f)", 
                fieldName, MIN_VALUE, MAX_VALUE, value)
            );
        }
    }
    
    public static void validateStep(double step) throws IntegralException {
        if (step <= 0) {
            throw new IntegralException("Шаг должен быть положительным числом! (Введено: " + step + ")");
        }
        validateStaticValue(step, "Шаг");
    }
    
    public double getLowerBound() { return lowerBound; }
    public double getUpperBound() { return upperBound; }
    public double getStep() { return step; }
    public double getResult() { return result; }
    
    public void setLowerBound(double lowerBound) throws IntegralException {
        validateValue(lowerBound, "Нижний предел");
        this.lowerBound = lowerBound;
    }
    
    public void setUpperBound(double upperBound) throws IntegralException {
        validateValue(upperBound, "Верхний предел");
        this.upperBound = upperBound;
    }
    
    public void setStep(double step) throws IntegralException {
        validateValue(step, "Шаг");
        this.step = step;
    }
    
    public void setResult(double result) {
        this.result = result;
    }
    
    @Override
    public String toString() {
        return lowerBound + ";" + upperBound + ";" + step + ";" + result;
    }
    
    public static RecIntegral fromString(String line) throws IntegralException {
        String[] parts = line.split(";");
        if (parts.length != 4) {
            throw new IntegralException("Неверный формат строки: " + line);
        }
        try {
            double lower = Double.parseDouble(parts[0]);
            double upper = Double.parseDouble(parts[1]);
            double step = Double.parseDouble(parts[2]);
            double result = Double.parseDouble(parts[3]);
            return new RecIntegral(lower, upper, step, result);
        } catch (NumberFormatException e) {
            throw new IntegralException("Ошибка парсинга чисел в строке: " + line);
        }
    }
}