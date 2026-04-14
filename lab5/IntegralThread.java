package com.mycompany.lb5;

import javax.swing.*;

public class IntegralThread extends Thread {
    
    private double a;
    private double b;
    private double n;
    private int row;
    private Lb5 parent;
    
    private double result;
    private boolean success;
    private String errorMessage;
    private boolean cancelled;  
    
    public IntegralThread(double a, double b, double n, int row, Lb5 parent) {
        this.a = a;
        this.b = b;
        this.n = n;
        this.row = row;
        this.parent = parent;
        this.success = false;
        this.cancelled = false;  
    }
    
    @Override
    public void run() {
        try {
            // прервал до вычисления
            if (Thread.currentThread().isInterrupted()) {
                cancelled = true;
                SwingUtilities.invokeLater(() -> parent.onCalculationCancelled(row));
                return;
            }
            
            result = Calculator.integral(a, b, n);
            
            // прервали в моменте
            if (Thread.currentThread().isInterrupted()) {
                cancelled = true;
                SwingUtilities.invokeLater(() -> parent.onCalculationCancelled(row));
                return;
            }
            
            success = true;
            
        } catch (IntegralException e) {
            if (Thread.currentThread().isInterrupted()) {
                cancelled = true;
                SwingUtilities.invokeLater(() -> parent.onCalculationCancelled(row));
                return;
            }
            errorMessage = e.getMessage();
            success = false;
        }
        
        // на всякий случай перед результатом
        if (Thread.currentThread().isInterrupted()) {
            cancelled = true;
            SwingUtilities.invokeLater(() -> parent.onCalculationCancelled(row));
            return;
        }
        
        final boolean finalSuccess = success;
        final double finalResult = result;
        final String finalError = errorMessage;
        
        SwingUtilities.invokeLater(() -> {
            if (cancelled) {
                parent.onCalculationCancelled(row);
            } else if (finalSuccess) {
                parent.onCalculationComplete(row, finalResult);
            } else {
                parent.onCalculationError(row, finalError);
            }
        });
    }
    
    public void cancel() {
        cancelled = true;
        this.interrupt();  
    }
    
    public boolean isCancelled() {
        return cancelled;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public double getResult() {
        return result;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
}