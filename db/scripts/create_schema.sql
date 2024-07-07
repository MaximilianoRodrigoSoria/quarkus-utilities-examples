IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'Personas' AND type = 'U')
    BEGIN
        CREATE TABLE Personas
        (
            id       INT PRIMARY KEY IDENTITY (1,1),
            nombre   NVARCHAR(50),
            apellido NVARCHAR(50),
            edad     INT,
            create_at DATETIME DEFAULT GETDATE(),
            update_at DATETIME DEFAULT GETDATE(),
            active   BIT      DEFAULT 1
        );
    END
GO