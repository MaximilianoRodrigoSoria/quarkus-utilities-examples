#!/bin/bash

# Esperar a que el servidor esté listo
sleep 30s

# Ejecutar el script para crear el esquema
/opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P YourStrong!Passw0rd -d master -i /scripts/create_schema.sql

# Ejecutar el script para llenar la base de datos
/opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P YourStrong!Passw0rd -d YourDatabaseName -i /scripts/data.sql
