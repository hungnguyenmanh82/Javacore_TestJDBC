package hung.com.all.table;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 * Phần này thư�?ng làm trên SQL workbench. Nên ko cần thiết
// EMP là tên database ko cần quote => nếu có quote sẽ báo lỗi
 sql> CREATE DATABASE IF NOT EXISTS EMP;
 
 sql> show databases;
 
 //sau khi dùng lệnh này có thể dùng tablename without databaseName
mysql> use databaseName;

SQL> DROP DATABASE DATABASE_NAME;
 */
public class App21_CreateTable {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	static final String DB_URL = "jdbc:mysql://localhost"; //jdbc:mysql://localhost:3306/TableName?autoReconnect=true&useSSL=false
	static final String USER = "root"; //root
	static final String PASS = "123456789"; //123456789


	public static void main(String[] args) {
		String databaseName = "testCreateDB";
		
		//test1: create database
		createDatabase(databaseName);

		//test2: create table on the data base which have just created above
		String sqlTable = "CREATE TABLE IF NOT EXISTS registration2 " +
				"(id INTEGER not NULL, " +
				" first VARCHAR(255), " + 
				" last VARCHAR(255), " + 
				" age INTEGER, " + 
				" PRIMARY KEY ( id ))";
		createTable(databaseName, sqlTable);

	}

	//mysql> show databases;

	private static String sqlCreateDatabase = "CREATE DATABASE IF NOT EXISTS "+ "databaseName" +";";
	private static String sqlDropDatabase = "DROP DATABASE IF EXISTS "+ "databaseName" +";";
	
	private static void executeRawSQL(String sql){
		Connection conn = null;
		Statement stmt = null;
		try{

			String sqlOption = "?autoReconnect=true&useSSL=false";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER, PASS);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);

			System.out.println("run successfully sql command: "+ sql);
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try

	}
	
	private static void createDatabase(String databaseName){
		String sql = "CREATE DATABASE IF NOT EXISTS "+ databaseName +";";
		executeRawSQL(sql);
	}

	private static void dropDatabase(String databaseName){
		String sql = "DROP DATABASE IF EXISTS "+ databaseName +";";
		executeRawSQL(sql);
	}
	
	//=============================================================
	private static String sqlTable1 = "CREATE TABLE REGISTRATION " +
			"(id INTEGER not NULL, " +
			" first VARCHAR(255), " + 
			" last VARCHAR(255), " + 
			" age INTEGER, " + 
			" PRIMARY KEY ( id ))"; 

	private static String sqlTable2 = "CREATE TABLE city (" +
			"ID int(11) NOT NULL AUTO_INCREMENT, "+ 
			"Name char(35) NOT NULL DEFAULT '', " +
			"CountryCode char(3) NOT NULL DEFAULT '', "+
			"District char(20) NOT NULL DEFAULT '', "+
			"Population int(11) NOT NULL DEFAULT '0', "+
			"PRIMARY KEY (`ID`),"+
			"KEY `CountryCode` (`CountryCode`),"+
			"CONSTRAINT `city_ibfk_1` FOREIGN KEY (`CountryCode`) REFERENCES `country` (`Code`)"+
			") ENGINE=InnoDB AUTO_INCREMENT=4080 DEFAULT CHARSET=latin1;";

	private static String sqlTable3 = "CREATE TABLE `city` ("+
			"`ID` int(11) NOT NULL AUTO_INCREMENT,"+
			"`Name` char(35) NOT NULL DEFAULT '',"+
			"`CountryCode` char(3) NOT NULL DEFAULT '',"+
			"`District` char(20) NOT NULL DEFAULT '',"+
			"`Population` int(11) NOT NULL DEFAULT '0',"+
			"PRIMARY KEY (`ID`),"+
			"KEY `CountryCode` (`CountryCode`),"+
			"INDEX `PopulationIndex` (`Population`)"+
			") ENGINE=InnoDB AUTO_INCREMENT=4080 DEFAULT CHARSET=latin1;";

    /*
     * step1: connect database => need @databaseName
     * step2: create table
     * */
	private static void createTable(String databaseName, String sqlTable){
		Connection conn = null;
		Statement stmt = null;
		try{
			//step1: connect database
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/"+ databaseName, USER, PASS);
			stmt = conn.createStatement();

			//step2: create table
			stmt.executeUpdate(sqlTable);
			System.out.println(String.format("jdbc:mysql://localhost/%s >%s", databaseName, sqlTable));
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					conn.close();
			}catch(SQLException se){
			}// do nothing
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try

	}



}
