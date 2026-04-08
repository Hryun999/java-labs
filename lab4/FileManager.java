package com.mycompany.lb4;

import javax.swing.*;
import java.io.*;
import java.util.List;

public class FileManager {
    
    // сохранение
    public static void saveToTextFile(List<RecIntegral> records, JFrame parent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Сохранить в текстовый файл");
        
        int result = fileChooser.showSaveDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (RecIntegral rec : records) {
                    writer.write(rec.toString());
                    writer.newLine();
                }
                JOptionPane.showMessageDialog(parent, "Сохранено в текстовый файл!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(parent, "Ошибка: " + ex.getMessage());
            }
        }
    }
    
    // загрузкай файла
    public static void loadFromTextFile(List<RecIntegral> records, JFrame parent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Загрузить из текстового файла");
        
        int result = fileChooser.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                records.clear();
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().isEmpty()) {
                        try {
                            RecIntegral rec = RecIntegral.fromString(line);
                            records.add(rec);
                        } catch (IntegralException e) {
                            JOptionPane.showMessageDialog(parent, "Ошибка в строке: " + line);
                        }
                    }
                }
                JOptionPane.showMessageDialog(parent, "Загружено " + records.size() + " записей");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(parent, "Ошибка: " + ex.getMessage());
            }
        }
    }
    
    // сохранение дес
    public static void saveToBinaryFile(List<RecIntegral> records, JFrame parent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Сохранить в двоичный файл");
        
        int result = fileChooser.showSaveDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            try (ObjectOutputStream out = new ObjectOutputStream(
                    new BufferedOutputStream(new FileOutputStream(file)))) {
                out.writeObject(records);
                JOptionPane.showMessageDialog(parent, "Сохранено в двоичный файл!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(parent, "Ошибка: " + ex.getMessage());
            }
        }
    }
    
    // загрузка дес
    @SuppressWarnings("unchecked")
    public static void loadFromBinaryFile(List<RecIntegral> records, JFrame parent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Загрузить из двоичного файла");
        
        int result = fileChooser.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            
            try (ObjectInputStream in = new ObjectInputStream(
                    new BufferedInputStream(new FileInputStream(file)))) {
                List<RecIntegral> loadedRecords = (List<RecIntegral>) in.readObject();
                records.clear();
                records.addAll(loadedRecords);
                JOptionPane.showMessageDialog(parent, "Загружено " + records.size() + " записей");
            } catch (IOException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(parent, "Ошибка: " + ex.getMessage());
            }
        }
    }
}