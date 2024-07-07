-- Seleccionar la base de datos
USE ExmapleDatabase;
GO

-- Insertar datos en la tabla Users
INSERT INTO Users (UserName, PasswordHash, Email) VALUES
                                                      ('user1', 'hash1', 'user1@example.com'),
                                                      ('user2', 'hash2', 'user2@example.com'),
                                                      ('user3', 'hash3', 'user3@example.com');
GO

-- Insertar datos en la tabla Products
INSERT INTO Products (ProductName, Price, Stock) VALUES
                                                     ('Product1', 10.99, 100),
                                                     ('Product2', 20.50, 200),
                                                     ('Product3', 30.00, 150);
GO
