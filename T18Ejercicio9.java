package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class T18Ejercicio9 {
	final static String DB_URL = "jdbc:mysql://localhost:3306/"; 
	final static String USERNAME = "root";
	final static String PASSWORD = ""; 
	
	static Connection conexion = null;
	static Statement stmt = null;
	
	public static Connection connect(String BD) {
	    try {
	    	conexion = DriverManager.getConnection(DB_URL+BD, USERNAME, PASSWORD);
	    	System.out.println("Conectado a la BD"); 
	    	
	         
	    }catch(SQLException ex) {
	    	System.out.println("No se ha podido conectar con la base de datos"); 
	    	System.out.println(ex);
	    }
	    return conexion;
	}
	
	public static Connection close() {
		if (conexion!=null) {
			try {
		    	conexion.close();
		    	System.out.println("BD cerrada correctamente"); 
		    	
		    }catch(SQLException ex) {
		    	System.out.println("Ha ocurrido un error al cerrar la BD"); 
		    	System.out.println(ex);
		    }
		}
	    return conexion;
	}
	
	public static void main (String args[]) {
		String BD; //nombre de la Base de Datos
		
       	try {
       		BD="";
       		conexion = connect(BD);
       		
       		stmt = conexion.createStatement();
       		
       		String sql = "drop database IF EXISTS Investigadores;";
       		stmt.execute(sql);
       		
       		BD="Investigadores";
       		sql = "create database "+BD+";";
       		stmt.execute(sql); //ejecutamos y creamos
       		System.out.println("La Base de Datos "+ BD +" ha sido creada");
       		
       	}catch(SQLException ex) {
       		System.out.println(ex);
       	}
       	
       	try {
       		System.out.println();
       		BD="Investigadores";
       		conexion = connect(BD);
       		
       		stmt = conexion.createStatement();
       		

       		String sql="create table IF NOT EXISTS Facultad (idFacultad INT AUTO_INCREMENT PRIMARY KEY NOT NULL, Nombre nvarchar(100));";
   			stmt.execute(sql); //ejecutamos APUNTE solo se puede ejecutar 1 por 1 y no todos a la vez
   			System.out.println("Tabla Facultad creada correctamente");

   			sql="create table IF NOT EXISTS Equipos (NumSerie char(4) PRIMARY KEY NOT NULL, Facultad int, Nombre nvarchar(100), FOREIGN KEY (Facultad) REFERENCES Facultad(idFacultad));";
   			stmt.execute(sql); //ejecutamos
   			System.out.println("Tabla Equipos creada correctamente");
   			
   			sql="create table IF NOT EXISTS Investigadores (DNI varchar(8) PRIMARY KEY NOT NULL, NomApels nvarchar(255), Facultad int, FOREIGN KEY (Facultad) REFERENCES Facultad(idFacultad));";
   			stmt.execute(sql); //ejecutamos
   			System.out.println("Tabla Investigadores creada correctamente");

   			sql="create table IF NOT EXISTS Reserva ( DNI varchar(8) NOT NULL, NumSerie char(4) NOT NULL, Comienzo datetime, Fin datetime, PRIMARY KEY (DNI,NumSerie), FOREIGN KEY (DNI) REFERENCES Investigadores(DNI), FOREIGN KEY (NumSerie) REFERENCES Equipos(NumSerie));";
   			stmt.execute(sql); //ejecutamos
   			System.out.println("Tabla Reserva creada correctamente");
   			

   			sql="INSERT INTO facultad VALUES(1,'Facultad de Reus');";
   			stmt.execute(sql);
   			sql="INSERT INTO facultad VALUES(2,'Facultad de Barcelona');";
   			stmt.execute(sql);
   			sql="INSERT INTO facultad VALUES(3,'Facultad de Malaga');";
   			stmt.execute(sql);
   			sql="INSERT INTO facultad VALUES(4,'Facultad de Sevilla');";
   			stmt.execute(sql);
   			sql="INSERT INTO facultad VALUES(5,'Facultad de Murcia');";
   			stmt.execute(sql);
   			
   			System.out.println("\nRegistros Facultad añadidos con exito");

   			sql="INSERT INTO equipos VALUES(1,5,'Generals');";
   			stmt.execute(sql);
   			sql="INSERT INTO equipos VALUES(2,4,'El equipo');";
   			stmt.execute(sql);
   			sql="INSERT INTO equipos VALUES(3,3,'Facultad de Malaga');";
   			stmt.execute(sql);
   			sql="INSERT INTO equipos VALUES(4,2,'Sevillanos');";
   			stmt.execute(sql);
   			sql="INSERT INTO equipos VALUES(5,1,'Los mejores');";
   			stmt.execute(sql);
   			
   			System.out.println("Registros Equipos añadidos con exito");
   			
   			sql="INSERT INTO investigadores VALUES(37484372A,'Laura Paguro',3);";
   			stmt.execute(sql);
   			sql="INSERT INTO investigadores VALUES(68029270F,'Jordi Girones',2);";
   			stmt.execute(sql);
   			sql="INSERT INTO investigadores VALUES(46768873X,'Sam Disousa',5);";
   			stmt.execute(sql);
   			sql="INSERT INTO investigadores VALUES(24624624A,'Jack Trevor',4);";
   			stmt.execute(sql);
   			sql="INSERT INTO investigadores VALUES(41231329B,'Biel Prous',1);";
   			stmt.execute(sql);
   			
   			System.out.println("Registros Investigadores añadidos con exito");
   			
   			sql="INSERT INTO reserva VALUES(41231329B,1,'2021-10-03 10:30:00','2021-11-12 12:30:00');";
   			stmt.execute(sql);
   			sql="INSERT INTO reserva VALUES(68029270F,2,'2021-02-10 10:30:00','2021-12-18 12:30:00');";
   			stmt.execute(sql);
   			sql="INSERT INTO reserva VALUES(37484372A,3,'2021-04-12 10:30:00','2021-12-10 12:30:00');";
   			stmt.execute(sql);
   			sql="INSERT INTO reserva VALUES(24624624A,4,'2021-01-05 10:30:00','2021-12-06 12:30:00');";
   			stmt.execute(sql);
   			sql="INSERT INTO reserva VALUES(46768873X,5,'2021-01-22 10:30:00','2021-08-01 12:30:00');";
   			stmt.execute(sql);
   			
   			System.out.println("Registros Reserva añadidos con exito\n");
   			
   			conexion = close();
       	}catch(SQLException ex) {
       		System.out.println(ex);
       	}
	}
}