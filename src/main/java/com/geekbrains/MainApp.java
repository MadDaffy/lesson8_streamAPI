package com.geekbrains;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

//        1. Создайте массив с набором слов, и с помощью Stream API найдите наиболее часто
//        встречающееся;
//        2. Создайте массив объектов типа Сотрудник (с полями Имя, Возраст, Зарплата) и вычислите
//        среднюю зарплату сотрудника;
//        3. Напишите метод, способный найти в массиве сотрудников из п. 2 найдите N самых старших
//        сотрудников и отпечатает в консоль сообщение вида “N самых старших сотрудников зовут:
//        имя1, имя2, имяN;”.

public class MainApp {
    public static void main(String[] args) {
        MainApp mainApp = new MainApp();
        //        1. Создайте массив с набором слов, и с помощью Stream API найдите наиболее часто
        //        встречающееся;
        String[] strings = {"A", "B", "C", "A", "B", "B"};
        System.out.println(mainApp.getMaxRepeatWord(strings));

        //        2. Создайте массив объектов типа Сотрудник (с полями Имя, Возраст, Зарплата) и вычислите
        //        среднюю зарплату сотрудника;
        Employee[] employeeList = {
                new Employee("Ivan", 40, 500_000.0),
                new Employee("Alex", 33, 400_000.0),
                new Employee("Semen", 22, 100_000.0),
                new Employee("Oleg", 27, 250_000.0)

        };
        System.out.println(mainApp.getAverageSalary(employeeList));

        //        3. Напишите метод, способный найти в массиве сотрудников из п. 2 найдите N самых старших
        //        сотрудников и отпечатает в консоль сообщение вида “N самых старших сотрудников зовут:
        //        имя1, имя2, имяN;”.
        mainApp.printOlderEmployee(employeeList, 3);
    }

    public String getMaxRepeatWord(String[] strings) {
        return Arrays.asList(strings).stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public Double getAverageSalary(Employee[] employeeList) {
        return Arrays.stream(employeeList).collect(Collectors.averagingDouble(Employee::getSalary));
    }

    public void printOlderEmployee(Employee[] employeeList, Integer amountEmployee) {
        System.out.println(Arrays.stream(employeeList)
                .sorted(Comparator.comparingInt(Employee::getAge).reversed())
                .limit(amountEmployee)
                .map(Employee::getName)
                .collect(Collectors.joining(", ", amountEmployee+ " самых старших сотрудников зовут: ", ". ")));
    }


}

