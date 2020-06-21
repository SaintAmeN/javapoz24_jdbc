package com.sda.javapoz24.dao;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Klasa ładuje parametry (wewnątrz) i rozpoczyna połączenie z bazą.
 *
 * Klasa z której pobieramy obiekt połączenia - potrzebujemy tego obiektu żeby móc wykonywać zapytania.
 */
public class MysqlDBConnection {
    private MysqlDBParameters parameters;
    private MysqlDataSource dataSource;

    public MysqlDBConnection() {
        prepareParameters(); // załaduj wszystko po stworzeniu instancji
    }

    private void prepareParameters(){
        // załadowanie z zasobów
        try {
            parameters = MysqlDBParameters.loadFromResources();
        } catch (IOException e) {
            System.err.println("Błąd : " + e.getMessage());
            System.exit(9); // zamknij program jeśli nie udało się załadować parametrów.
        }

        dataSource = new MysqlDataSource();
        dataSource.setUser(parameters.getUsername());
        dataSource.setPassword(parameters.getPassword());
        dataSource.setDatabaseName(parameters.getDbName());
        dataSource.setServerName(parameters.getHost());
        dataSource.setPort(parameters.getPort());

        // parametry dodatkowe
        try {
            dataSource.setCreateDatabaseIfNotExist(true); //jeśli baza nie istnieje, stwórz ją
            dataSource.setServerTimezone("Europe/Warsaw");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Na podstawie dancyh z parametrów stwórz połączenie.
     * @return - obiekt połączenia.
     */
    public Connection createConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
