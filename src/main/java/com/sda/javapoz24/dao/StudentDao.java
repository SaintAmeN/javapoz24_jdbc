package com.sda.javapoz24.dao;


import com.sda.javapoz24.model.Gender;
import com.sda.javapoz24.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Gdy tworzę instancję tej klasy to moim celem jest uzyskanie dostępu do bazy.
 * <p>
 * Jeśli chcę manipulować obiektami w bazie danych, to muszę stworzyć sobie instancję tej klasy i wywołać jedną z metod CRUD.
 */
public class StudentDao {
    private MysqlDBConnection connector;

    public StudentDao(MysqlDBConnection connection) {
        this.connector = connection;

        createDatabaseAndTable();
    }

    private void createDatabaseAndTable() {
        try {
            Connection connection = connector.createConnection();

            PreparedStatement statement = connection.prepareStatement(StudentQuerries.CREATE_TABLE);
            statement.execute();

            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // C
    public void insertStudent(Student student) {
        // dzięki takiemu zapisowi, obiekt connection wywoła metodę close przed zakończeniem/zamykającą klamrą try
        try (Connection connection = connector.createConnection()) {
            // drugi parametr mówi, że po wstawieniu rekrdu spodziewamy się otrzymać wygenerowane ID
            PreparedStatement preparedStatement = connection.prepareStatement(StudentQuerries.INSERT_STUDENT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setBoolean(4, student.isAwarded());
            preparedStatement.setString(5, student.getGender().toString());

            int affectedRecords = preparedStatement.executeUpdate();
            // affectedRecords - ile rekordów zostało zmienionych
            System.out.println("Dodanych rekordów: " + affectedRecords);

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long identifier = generatedKeys.getLong(1);
                student.setId(identifier);

                System.out.println("Generated id: " + student.getId());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // R
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = connector.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(StudentQuerries.SELECT_STUDENTS);

            ResultSet rekordy = preparedStatement.executeQuery();

            // dopóki są rekordy
            while (rekordy.next()) {
                Student student = new Student();
                student.setId(rekordy.getLong(1));
                student.setFirstName(rekordy.getString(2));
                student.setLastName(rekordy.getString(3));
                student.setAge(Integer.parseInt(rekordy.getString(4)));
                student.setAwarded(Boolean.parseBoolean(rekordy.getString(5)));
                student.setGender(Gender.valueOf(rekordy.getString(6)));

                // ^^ załadowanie wartości z kolumn do obiektu
                // umieszczenie obiektu w liście:
                students.add(student);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return students;
    }

    // D
    public void deleteStudent(long identifier){
        try (Connection connection = connector.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(StudentQuerries.DELETE_STUDENT);
            preparedStatement.setLong(1, identifier); //wstawiamy identyfikator usuwanego rekrodu

            int affectedRecords = preparedStatement.executeUpdate();
            System.out.println("Usuniętych rekordów: " + affectedRecords);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // TODO: Update
}
