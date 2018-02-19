import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

/**
Author: Prativas Basu 
@email : dev.prativas@gmail.com

how to run  : 1) Download Connector/J Platform Independent from https://dev.mysql.com/downloads/connector/j/
              2) Extract and put mysql-connector-java-5.1.45-bin.jar  in the same folder
              3) for compilation run in cmd prompt : javac -cp  mysql-connector-java-5.1.45-bin.jar;. CreateTable.java
              4) To run java -cp  mysql-connector-java-5.1.45-bin.jar;. CreateTable
@date : 19/02/2018 
 */




public class CreateTable {
	public static void main(String[] args) {
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//serverhost = localhost, port=3306, username=root, password=123
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/java_test","root","15081947");
			Statement smt=cn.createStatement();
			//query to create table Employees with fields name(empid,empname,dob,city,salary)
			String q="create table Employees1(empid varchar(10) primary key,empname varchar(45),dob date,city varchar(45),salary varchar(45))";
			//to execute the update
			smt.executeUpdate(q);
			System.out.println("Table Created....");
			cn.close();

		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}