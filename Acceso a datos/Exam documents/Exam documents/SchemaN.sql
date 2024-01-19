CREATE TABLE Players (
    Player_ID INT PRIMARY KEY,
    Name VARCHAR(100),
    Email VARCHAR(100),
    Tokens INT
);

CREATE TABLE Characters (
    Character_ID INT PRIMARY KEY,
    Name VARCHAR(100),
    Type VARCHAR(100),
    Level INT,
    Player_ID INT,
    FOREIGN KEY (Player_ID) REFERENCES Players(Player_ID)
);

CREATE TABLE Missions (
    Mission_ID INT PRIMARY KEY,
    Name VARCHAR(50),
    Description VARCHAR(150),
    Tokens INT
);

CREATE TABLE Completed_Missions (
    Mission_ID INT,
    Character_ID INT,
    Completion_Date DATE,
    PRIMARY KEY (Mission_ID, Character_ID),
    FOREIGN KEY (Mission_ID) REFERENCES Missions(Mission_ID),
    FOREIGN KEY (Character_ID) REFERENCES Characters(Character_ID)
);

-- Insertando datos en la tabla Players
INSERT INTO Players (Player_ID, Name, Email, Tokens) 
VALUES (1, 'John Doe', 'johndoe@example.com', 100),
       (2, 'Jane Doe', 'janedoe@example.com', 200),
       (3, 'Jim Doe', 'jimdoe@example.com', 300);

-- Insertando datos en la tabla Characters
INSERT INTO Characters (Character_ID, Name, Type, Level, Player_ID) 
VALUES (1, 'Warrior', 'Elf', 10, 1),
       (2, 'Mage', 'Human', 15, 2),
       (3, 'Rogue', 'Dwarf', 20, 3),
	(4, 'Luke', 'Dog', 5, 3);
	
-- Insertando datos en la tabla Missions
INSERT INTO Missions (Mission_ID, Name, Description, Tokens) 
VALUES (1, 'First Mission', 'This is the first mission.', 50),
       (2, 'Second Mission', 'This is the second mission.', 75),
       (3, 'Third Mission', 'This is the third mission.', 100);

-- Insertando datos en la tabla Completed_Missions
INSERT INTO Completed_Missions (Mission_ID, Character_ID, Completion_Date) 
VALUES (1, 1, '2023-11-13'),
       (2, 2, '2023-11-14'),
       (3, 3, '2023-11-15');

