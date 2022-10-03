package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class T18Ejercicio6 {
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
		String BD; 
		
       	try {
       		BD="";
       		conexion = connect(BD);
       		
       		stmt = conexion.createStatement();
       		
       		String sql = " drop database IF EXISTS Piezas_proveedores;";
       		stmt.execute(sql); //ejecutamos y borramos
       		
       		BD="Piezas_proveedores";
       		sql = "create database "+BD+";
       		stmt.execute(sql); //ejecutamos y creamos
       		System.out.println("La Base de Datos "+ BD +" ha sido creada");
       		
       	}catch(SQLException ex) {
       		System.out.println(ex);
       	}
       	
       	try { //Añadimos las TABLAS+REGISTROS a la Base de Datos
       		System.out.println();
       		BD="Piezas_proveedores";
       		conexion = connect(BD);
       		
       		stmt = conexion.createStatement();
       		
       		String sql="create table IF NOT EXISTS Piezas (idPieza INT AUTO_INCREMENT PRIMARY KEY NOT NULL, Nombre nvarchar(100));";
   			stmt.execute(sql); //ejecutamos APUNTE solo se puede ejecutar 1 por 1 y no todos a la vez
   			System.out.println("Tabla Piezas creada correctamente");
   			
   			//creamos la tabla Proveedores
   			sql="create table IF NOT EXISTS Proveedores (idPorveedor char(4) PRIMARY KEY NOT NULL, Nombre nvarchar(100));";
   			stmt.execute(sql); //ejecutamos
   			System.out.println("Tabla Proveedores creada correctamente");
   			
   			//creamos la tabla Suministra
   			sql="create table IF NOT EXISTS Suministra (idPieza int, idPorveedor char(4), Precio int, PRIMARY KEY (idPieza,idPorveedor), FOREIGN KEY (idPieza) REFERENCES Piezas(idPieza), FOREIGN KEY (idPorveedor) REFERENCES Proveedores(idPorveedor));";
   			stmt.execute(sql); //ejecutamos
   			System.out.println("Tabla Suministra creada correctamente");
   			

   			sql="INSERT INTO piezas VALUES(1,'Pantalla');";
   			stmt.execute(sql);
   			sql="INSERT INTO piezas VALUES(2,'CPU');";
   			stmt.execute(sql);
   			sql="INSERT INTO piezas VALUES(3,'Teclado');";
   			stmt.execute(sql);
   			sql="INSERT INTO piezas VALUES(4,'Raton');";
   			stmt.execute(sql);
   			sql="INSERT INTO piezas VALUES(5,'Placa Base');";
   			stmt.execute(sql);
   			
   			System.out.println("\nRegistros Piezas añadidos con exito");
   			
   			sql="INSERT INTO proveedores VALUES(1,'Intel');";
   			stmt.execute(sql);
   			sql="INSERT INTO proveedores VALUES(2,'Amd');";
   			stmt.execute(sql);
   			sql="INSERT INTO proveedores VALUES(3,'Razer');";
   			stmt.execute(sql);
   			sql="INSERT INTO proveedores VALUES(4,'Trust');";
   			stmt.execute(sql);
   			sql="INSERT INTO proveedores VALUES(5,'AeroCool');";
   			stmt.execute(sql);
   			
   			System.out.println("Registros Proveedores añadidos con exito");
   			
   			//Registros Suministra
   			sql="INSERT INTO suministra VALUES(1,2,'39');";
   			stmt.execute(sql);
   			sql="INSERT INTO suministra VALUES(2,4,'120');";
   			stmt.execute(sql);
   			sql="INSERT INTO suministra VALUES(3,5,'500');";
   			stmt.execute(sql);
   			sql="INSERT INTO suministra VALUES(4,3,'125');";
   			stmt.execute(sql);
   			sql="INSERT INTO suministra VALUES(5,1,'119');";
   			stmt.execute(sql);
   			
   			System.out.println("Registros Suministra añadidos con exito\n");
   			
   			conexion = close();	//Cerramos la BD
       	}catch(SQLException ex) {
       		System.out.println(ex);
       	}
	}
}