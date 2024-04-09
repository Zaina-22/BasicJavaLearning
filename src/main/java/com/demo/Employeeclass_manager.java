package com.demo;

import java.io.*;
import java.util.*;

public class Employeeclass_manager {

    public static void main(String[] args) {
        /* Step:1:Read csv data into array of string
           Step2: Parse Array of string and create list of employee objects
           Step3: Store employee in different collections-list,map
           Step4: Modify data
           Step5:Write in csv
         */

        List<String[]> csvData = readCSV("src/Resources/employee.csv");
        List<Employee> employees = parseCSV(csvData);
        List<Employee> employeeList=new ArrayList<>(employees);
        Map<Integer,Employee> employeeMap=new HashMap<>();
        for(Employee employee:employees){
            employeeMap.put(employee.getId(),employee);
        }

        Employee employee=employeeMap.get(1);
        double inc_sal=employee.getSalary()*5;
        employee.setSalary(inc_sal);
        System.out.println(employeeMap.get(1));

        writeCSV("src/Resources/updates_employee",employees);
    }


    public static List<String[]> readCSV(String filename) {
        List<String[]> data = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String line;
        try {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                data.add(values);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    public static List<Employee> parseCSV(List<String[]> csvData){
        List<Employee>employees=new ArrayList<>();
        for(String[] row:csvData){
            int id= Integer.parseInt(row[0]);
            String name=row[1];
            Double salary= Double.valueOf(row[2]);
            String DateofJoining=row[3];
            Employee employee=new Employee(id,name,salary,DateofJoining);
            employees.add(employee);
        }
    return employees;
    }

    public static void writeCSV(String filename, List<Employee> employees) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Employee employee : employees) {
                writer.write(employee.getId() + "," + employee.getName() + "," + employee.getSalary() + "," + employee.getDateofJoining());
                writer.newLine();
            }
            System.out.println("Data has been written to the file successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static class Employee{
        private int id;
        private String name;
        private double salary;
        private String DateofJoining;

        public Employee(int id, String name, double salary, String dateofJoining) {
            this.id = id;
            this.name = name;
            this.salary = salary;
            this.DateofJoining = dateofJoining;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        public String getDateofJoining() {
            return DateofJoining;
        }

    }
}
