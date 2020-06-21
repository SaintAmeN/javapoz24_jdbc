package com.sda.javapoz24;

import lombok.*;

// Getter, Setter, Data, AllArgsConstructor, ToString, EqualsAndHashCode, NoArgsConstructor
//@Getter
//@Setter
//@ToString
//@EqualsAndHashCode
@Data //Getter, Setter ToString EqualsAndHashCode, RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    // POJO - Plain Old Java Object
    //          -- prywatne pola
    //          -- gettery i settery do pól
    //          -- pusty konstruktor (min.)
    private Long id;

    private String firstName;
    private String lastName;
    private int age;

    private boolean awarded;

    private Gender gender;

}
