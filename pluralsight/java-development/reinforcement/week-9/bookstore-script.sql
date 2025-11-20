-- SQL Script for "Mysterious Bookstore" Database

-- Drop the database if it exists
DROP DATABASE IF EXISTS mysterious_bookstore;

-- Create the MysteriousBookstore database
CREATE DATABASE IF NOT EXISTS mysterious_bookstore;

-- Switch to the MysteriousBookstore database
USE mysterious_bookstore;

-- Drop tables if they exist (in dependency order)
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Customers;
DROP TABLE IF EXISTS Books;
DROP TABLE IF EXISTS Categories;
DROP TABLE IF EXISTS Authors;

-- Creating the 'Authors' table
CREATE TABLE Authors (
    AuthorID VARCHAR(10) PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Country VARCHAR(255)
);

-- Creating the 'Categories' table
CREATE TABLE Categories (
    CategoryID VARCHAR(10) PRIMARY KEY,
    CategoryName VARCHAR(255) NOT NULL
);

-- Creating the 'Books' table
CREATE TABLE Books (
    BookID VARCHAR(10) PRIMARY KEY,
    Title VARCHAR(255) NOT NULL,
    AuthorID VARCHAR(10) NOT NULL,
    CategoryID VARCHAR(10) NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (AuthorID) REFERENCES Authors(AuthorID),
    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
);

-- Creating the 'Customers' table
CREATE TABLE Customers (
    CustomerID VARCHAR(10) PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Email VARCHAR(255)
);

-- Creating the 'Orders' table
CREATE TABLE Orders (
    OrderID INT PRIMARY KEY AUTO_INCREMENT,
    BookID VARCHAR(10) NOT NULL,
    CustomerID VARCHAR(10) NOT NULL,
    Quantity INT NOT NULL,
    OrderDate DATE,
    FOREIGN KEY (BookID) REFERENCES Books(BookID),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);


-- Inserting sample data into 'Authors'
INSERT INTO Authors (AuthorID, Name, Country) VALUES
('A01', 'John Doe', 'USA'),
('A02', 'Emily Carter', 'UK'),
('A03', 'Alex Smith', 'Canada');

-- Inserting sample data into 'Categories'
INSERT INTO Categories (CategoryID, CategoryName) VALUES
('C01', 'Mystery'),
('C02', 'Adventure'),
('C03', 'Educational');

-- Inserting sample data into 'Books'
INSERT INTO Books (BookID, Title, AuthorID, CategoryID, Price) VALUES
('B101', 'Mystery of the Old Library', 'A01', 'C01', 15.99),
('B102', 'The Lost Treasure', 'A02', 'C02', 9.99),
('B103', 'Adventures in SQL', 'A03', 'C03', 20.50),
('B104', 'Shadows in the Attic','A01', 'C01', 17.50),
('B105', 'Whispers in the Dark','A01', 'C01', 18.25),
('B106', 'Journey to the Unknown','A03', 'C02', 12.99);

-- Inserting sample data into 'Customers'
INSERT INTO Customers (CustomerID, Name, Email) VALUES
('CU01', 'Alice Brown', 'alice.brown@email.com'),
('CU02', 'Bob Johnson', 'bob.johnson@email.com'),
('CU03', 'Emily Carter', 'emily.carter@email.com');

-- Inserting sample data into 'Orders'
INSERT INTO Orders (BookID, CustomerID, Quantity, OrderDate) VALUES
('B102', 'CU03', 1, '2023-05-15'),
('B101', 'CU02', 2, '2023-05-16'),
('B104', 'CU03', 1, '2023-06-01'),
('B106', 'CU01', 3, '2023-06-10');

-- The database is now set up with sample data
