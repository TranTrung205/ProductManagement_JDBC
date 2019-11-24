-- Drop tables
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS Category;

-- Category table
CREATE TABLE Category(ID INT PRIMARY KEY AUTO_INCREMENT, Name TEXT);
INSERT INTO Category(Name) VALUES('Laptop');
INSERT INTO Category(Name) VALUES('Smartphone');
INSERT INTO Category(Name) VALUES('Assessory');

-- Product table
CREATE TABLE Product(
ID INT PRIMARY KEY AUTO_INCREMENT, 
Name TEXT, 
Price INT, 
CatID INT, 
FOREIGN KEY (CatID) REFERENCES Category(ID) ON DELETE CASCADE
);
INSERT INTO Product(Name, Price, CatID) VALUES('Macbook Air', 999, 1);
INSERT INTO Product(Name, Price, CatID) VALUES('Macbook Pro', 1999, 1);
INSERT INTO Product(Name, Price, CatID) VALUES('iPhone X', 999, 2);
INSERT INTO Product(Name, Price, CatID) VALUES('iPhone Xs', 1999, 2);
INSERT INTO Product(Name, Price, CatID) VALUES('iPhone 11', 2999, 2);
INSERT INTO Product(Name, Price, CatID) VALUES('Anker battery', 50, 3);
INSERT INTO Product(Name, Price, CatID) VALUES('Marvel case', 10, 3);