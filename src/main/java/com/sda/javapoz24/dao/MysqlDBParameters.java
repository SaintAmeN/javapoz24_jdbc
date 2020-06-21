package com.sda.javapoz24.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MysqlDBParameters {
    private final static String PROPERTIES_PATH = "./jdbc.properties";

    private String username;
    private String password;
    private String dbName; // database name
    private String host;
    private int port;

    /**
     * Metoda ładuje z pliku "jdbc.properties" właściwości i zwraca na ich podstawie obiekt MysqlDBParameters.
     * @return - gotowy obiekt
     * @throws IOException - występuje kiedy pliku nie ma, ma zły format
     */
    static MysqlDBParameters loadFromResources() throws IOException {
        MysqlDBParameters dbParameters = new MysqlDBParameters();
//        .. załadowanie obiektu z resources/jdbc.properties
//        Pliki ładuje się strumieniami - resource as stream
        Properties properties = new Properties();
        InputStream stream = MysqlDBParameters.class.getResourceAsStream(PROPERTIES_PATH);

        if(stream != null) {                // udało się znaleźć plik
            properties.load(stream);        //  wczytaj plik i załaduj do klasy

            // jestem po załadowaniu
            dbParameters.setUsername(properties.getProperty("database.jdbc.username"));
            dbParameters.setPassword(properties.getProperty("database.jdbc.password"));
            dbParameters.setDbName(properties.getProperty("database.jdbc.dbname"));
            dbParameters.setHost(properties.getProperty("database.jdbc.host"));
            dbParameters.setPort(Integer.parseInt(properties.getProperty("database.jdbc.port")));
        }
        return dbParameters;
    }
}
