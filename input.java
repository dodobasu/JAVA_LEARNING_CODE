/*
Take input until space
*/

import java.util.Scanner;
public class input
{
	public static void main(String[] args){

	Scanner sc = new Scanner(System.in);
	System.out.println("Enter you Skills");
	String skills = sc.next();
	System.out.println("your skills are " + skills);
	}
}