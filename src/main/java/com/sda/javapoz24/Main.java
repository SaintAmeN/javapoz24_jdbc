package com.sda.javapoz24;

import com.sda.javapoz24.dao.MysqlDBConnection;
import com.sda.javapoz24.dao.StudentDao;
import com.sda.javapoz24.model.Gender;
import com.sda.javapoz24.model.Student;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 1. Model - implementacja
        // 2. Zdefiniowanie parametrów bazy danych
        //      a. tworzymy klasę która odczyta z resources parametry bazy
        //      b. tworzymy klasę która z użyciem mysqldatasource stworzy połączenie
        // 3. Tworzymy zapytania do bazy danych.
        // 4. Tworzymy DAO
        // -- test (weryfikacja że tabela i baza się tworzy)
        // 5. Tworzymy (zapytania+) metodę insert

        StudentDao dao = new StudentDao(new MysqlDBConnection());
        Scanner scanner = new Scanner(System.in);

        String command;

        do {
            command = scanner.nextLine();

            if (command.startsWith("insert")) {                   // insert Paweł Gaweł 20 true MALE
                                                                  // 0      1     2     3  4    5
                String[] words = command.split(" ");
                Student student = Student.builder()
                        .firstName(words[1])
                        .lastName(words[2])
                        .age(Integer.parseInt(words[3]))
                        .awarded(Boolean.parseBoolean(words[4]))
                        .gender(Gender.valueOf(words[5].toUpperCase()))
                        .build();

                dao.insertStudent(student);
            }

        } while (!command.equalsIgnoreCase("quit"));
    }
}
