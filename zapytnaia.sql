-- stwóz bazę danych o nazwie `jpoz24_students_jdbc` jeśli nie istnieje
create database if not exists `jpoz24_students_jdbc`;

use `jpoz24_students_jdbc`;

-- stwórz tabelę `student` jeśli nie istnieje   
create table if not exists `student`  (
`id` integer not null primary key auto_increment,
`firstName` varchar(255),
`lastName` varchar(255),
`age` integer,
`awarded` boolean,
`gender` varchar(15));

-- wstawianie rekordu 
INSERT INTO `jpoz24_students_jdbc`.`student`
(`firstName`, `lastName`, `age`, `awarded`, `gender`) VALUES (?, ?, ?, ?, ?);

-- pobierz wszystkie rekordy
SELECT * from `student`;

-- usun rekord o podanym id
DELETE FROM `jpoz24_students_jdbc`.`student` WHERE `id`=?;

-- aktualizacja rekrodu
 UPDATE `student` SET `firstName` = ?, `lastName` = ?,  `age` = ?, `awarded` = ?, `gender` = ? WHERE `id` = ?;

-- pobierz po id
SELECT * FROM `student` where `id` = ?;
