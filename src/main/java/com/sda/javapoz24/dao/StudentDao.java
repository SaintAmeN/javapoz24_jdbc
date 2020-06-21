package com.sda.javapoz24.dao;


import com.sda.javapoz24.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Gdy tworzę instancję tej klasy to moim celem jest uzyskanie dostępu do bazy.
 *
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

    public void insertStudent(Student student){
        // dzięki takiemu zapisowi, obiekt connection wywoła metodę close przed zakończeniem/zamykającą klamrą try
        try(Connection connection = connector.createConnection()){
            // drugi parametr mówi, że po wstawieniu rekrdu spodziewamy się otrzymać wygenerowane ID
            PreparedStatement preparedStatement = connection.prepareStatement(StudentQuerries.INSERT_STUDENT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setBoolean(4, student.isAwarded());
            preparedStatement.setString(5, student.getGender().toString());

            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
