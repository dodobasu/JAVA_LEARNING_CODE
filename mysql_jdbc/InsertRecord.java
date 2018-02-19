import java.io.DataInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
Author: Prativas Basu 
@email : dev.prativas@gmail.com

how to run  : 1) Download Connector/J Platform Independent from https://dev.mysql.com/downloads/connector/j/
              2) Extract and put mysql-connector-java-5.1.45-bin.jar  in the same folder
              3) for compilation run in cmd prompt : javac -cp  mysql-connector-java-5.1.45-bin.jar;. InsertRecord.java
              4) To run java -cp  mysql-connector-java-5.1.45-bin.jar;. InsertRecord
@date : 19/02/2018 
 */

public class InsertRecord {
	public static void main(String[] args) {
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/java_test","root","15081947");
			Statement smt=cn.createStatement();
			DataInputStream KB=new DataInputStream(System.in);
			System.out.print("Enter Employee ID:");
			String eid=KB.readLine();

			System.out.print("Enter Employee Name:");
			String en=KB.readLine();

			System.out.print("Enter Employee Date Of Birth:");
			String ed=KB.readLine();

			System.out.print("Enter Employee City:");
			String ec=KB.readLine();


			System.out.print("Enter Employee Salary:");
			String es=KB.readLine();

			String q="insert into employees values('"+eid+"','"+en+"','"+ed+"','"+ec+"',"+es+")";
			System.out.println(q);

			smt.executeUpdate(q);

			System.out.println("Record Submitted....");

			cn.close();
		}catch(Exception e){
			System.out.println(e);  
		}		
	}
}