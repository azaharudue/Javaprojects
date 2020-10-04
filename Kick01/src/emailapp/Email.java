/**
 * 
 */
package emailapp;

import java.util.Scanner;

/**
 * @author Azahar Hossain.
 *
 */
public class Email {
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String department;
	private int mailboxCapacity;
	private int defaultPasswordLength=10;
	private String alternateEmail;
	private String companySuffix="SomeCompany.com";

// constructor to recieve the first name and last name 
	public Email(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		// calling constructor
		//System.out.println("Email created:" + this.firstName + " " + this.lastName);
		// calling a method asking for the department - return the department
		this.department=setDepartment();
		System.out.println("DEPARTMENT: "+this.department);
		// calling a method that returns a random password
		this.password = randomPassword(defaultPasswordLength);
		System.out.println("Your password is :"+this.password);
		//combine elements to generate email:
		 email = firstName.toLowerCase()+"."+ lastName.toLowerCase()+"@"+department+"."+companySuffix;
	     System.out.println("Your E-mail is: "+email);
	}
	
		// ask for the department
	private String setDepartment() {
		System.out.println("Department codes:\n1 for sale. \n2 for Development \n3 for Accounting\n0 for none\nEnter the department code:");
		Scanner in= new Scanner(System.in);
		int depChoice = in.nextInt();
		in.close();
		if (depChoice==1) {return "sal";}
		else if (depChoice == 2) {return "dev";}
		else if (depChoice == 3) { return "acc";}
		else {return "";}
		
		
	}
	private String randomPassword(int length) {
		String passwordSet="ABCDEFGHIJKLMNOPQRSTUVWXYZ012345678!;:-.#+´ß/*$%&/()=)?";
		char[] password= new char[length];
		for(int i=0;i<length;i++) {
			int rand=(int)(Math.random() * passwordSet.length());
			password[i]=passwordSet.charAt(rand);
			
			
		}
		return new String (password);
	}
	
}
