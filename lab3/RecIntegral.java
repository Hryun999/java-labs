package com.mycompany.lb3;

public class RecIntegral {
    private double lowerBound;
    private double upperBound;
    private double step;
    private double result;
    
    // Константы для диапазона
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
    
    private void validateValue(double value, String fieldName) 
            throws IntegralException {
        if (value < MIN_VALUE || value > MAX_VALUE) {
            throw new IntegralException(
                String.format("%s должен быть в диапазоне от %f до %f! (Введено: %f)", 
                fieldName, MIN_VALUE, MAX_VALUE, value)
            );
        }
    }
    
    private static void validateStaticValue(double value, String fieldName) 
            throws IntegralException {
        if (value < MIN_VALUE || value > MAX_VALUE) {
            throw new IntegralException(
                String.format("%s должен быть в диапазоне от %f до %f! (Введено: %f)", 
                fieldName, MIN_VALUE, MAX_VALUE, value)
            );
        }
    }
    
    public double getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(double lowerBound) throws IntegralException {
        validateValue(lowerBound, "Нижний предел");
        this.lowerBound = lowerBound;
    }

    public double getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(double upperBound) throws IntegralException {
        validateValue(upperBound, "Верхний предел");
        this.upperBound = upperBound;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) throws IntegralException {
        validateValue(step, "Шаг");
        this.step = step;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
    
    public static void validateStep(double step) throws IntegralException {
        if (step <= 0) {
            throw new IntegralException("Шаг должен быть положительным числом! (Введено: " + step + ")");
        }
        validateStaticValue(step, "Шаг");
    }
}