package com.sda.javapoz24;

import com.sda.javapoz24.dao.MysqlDBConnection;
import com.sda.javapoz24.dao.StudentDao;
import com.sda.javapoz24.model.Gender;
import com.sda.javapoz24.model.Student;

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

        dao.insertStudent(new Student(null, "Paweł", "Gaweł", 20, true, Gender.MALE));

        // tabela powinna zostać automatycznie stworzona.

    }
}
