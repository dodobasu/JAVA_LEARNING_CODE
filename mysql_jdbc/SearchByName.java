
import java.io.DataInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
Author: Prativas Basu 
@email : dev.prativas@gmail.com

how to run  : 1) Download Connector/J Platform Independent from https://dev.mysql.com/downloads/connector/j/
              2) Extract and put mysql-connector-java-5.1.45-bin.jar  in the same folder
              3) for compilation run in cmd prompt : javac -cp  mysql-connector-java-5.1.45-bin.jar;. SearchByName.java
              4) To run java -cp  mysql-connector-java-5.1.45-bin.jar;. SearchByName
@date : 19/02/2018 
 */

public class SearchByName {
	public static void main(String[] args) {
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			//serverhost = localhost, port=3306, username=root, password=123
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/java_test","root","15081947");

			Statement smt=cn.createStatement();
			//creating object of DataInputStream

			DataInputStream KB=new DataInputStream(System.in);
			//input id
			System.out.print("Name: ");
			String name=KB.readLine();
			

			//query to select salary between minimum and maximum values
			String q="Select * from employees where empname like'"+name+"%'";

			// to execute query
			ResultSet rs=smt.executeQuery(q);
			//to print the resultset on console
			if(rs.next())
			{
				do{
					System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5));
				}while(rs.next());
			}
			else
			{
				System.out.println("Record Not Found...");
			}
			cn.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}