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
              3) for compilation run in cmd prompt : javac -cp  mysql-connector-java-5.1.45-bin.jar;. EditRecord.java
              4) To run java -cp  mysql-connector-java-5.1.45-bin.jar;. EditRecord
@date : 19/02/2018 
 */

public class EditRecord {
	public static void main(String[] args) {
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			//serverhost = localhost, port=3306, username=root, password=123
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/java_test","root","15081947");

			Statement smt=cn.createStatement();

			DataInputStream KB=new DataInputStream(System.in);

			//input employee id by which we are going to edit record
			System.out.print("Enter Employee ID:");
			String eid=KB.readLine();

			//query to take data of a particular record from table employee
			String q="Select * from employees where empid='"+eid+"'";

			//to execute query
			ResultSet rs=smt.executeQuery(q);
			if(rs.next())
			{
				//to show the data
				System.out.println("Employee id:"+rs.getString(1));
				System.out.println("1.Employee Name:"+rs.getString(2));
				System.out.println("2.Employee DOB:"+rs.getString(3));
				System.out.println("3.Employee City:"+rs.getString(4));
				System.out.println("4.Employee Salary:"+rs.getString(5));
				System.out.println("5.Exit");
				System.out.println("Which Field U Want to Edit?");
				String ch=KB.readLine();
				String pat="";
				//cases to choose field you want to edit
				switch(ch)
				{ 
					case "1":
						System.out.print("Enter New Name:");
						String nn=KB.readLine();
						pat="empname='"+nn+"'";
						break;

					case "2":
						System.out.print("Enter New DOB:");
						String nd=KB.readLine();
						pat="dob='"+nd+"'";
						break;

					case "3":
						System.out.print("Enter New City:");
						String nc=KB.readLine();
						pat="city='"+nc+"'";
						break;

					case "4":
						System.out.print("Enter New Salary:");
						String ns=KB.readLine();
						pat="salary="+ns;
						break;

					case "5":
						System.out.println("Exit");
						break;

					default:
						System.out.println("Wrong Option");
						break;
				}
				if(!pat.equals(""))
				{
					//query to edit data of a particular record from table employee
					q="update employees set "+pat+" where empid='"+eid+"'";
					//to execute update
					smt.executeUpdate(q);
					System.out.println("Record Updated....");
				}
			}
			else
			{
				System.out.println("Record Not Found...");
			}
			cn.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}