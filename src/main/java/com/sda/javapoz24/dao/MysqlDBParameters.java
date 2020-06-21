package com.sda.javapoz24.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MysqlDBParameters {
    private String username;
    private String password;
    private String dbName; // database name
    private String host;
    private int port;

    static MysqlDBParameters loadFromResources(){
//        .. załadowanie obiektu z resources/jdbc.properties

    }

}
