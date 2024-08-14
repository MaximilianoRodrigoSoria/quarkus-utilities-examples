IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'personas' AND type = 'U')
    BEGIN
        CREATE TABLE personas
        (
            id       INT PRIMARY KEY IDENTITY (1,1),
            nombre   NVARCHAR(50),
            apellido NVARCHAR(50),
            edad     INT,
            created_at DATETIME DEFAULT GETDATE(),
            updated_at DATETIME DEFAULT GETDATE(),
            active   BIT      DEFAULT 1
        );
    END
GO