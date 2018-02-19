
/*
Take input until return
*/
import java.util.Scanner;
public class NextLine
{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your name");
		String name = sc.nextLine();
		System.out.println("your name is " + name);
	}
}