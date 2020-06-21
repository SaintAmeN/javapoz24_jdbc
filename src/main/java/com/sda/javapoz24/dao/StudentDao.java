package com.sda.javapoz24.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
