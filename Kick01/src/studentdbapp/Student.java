package studentdbapp;

import java.text.ParseException;
import java.util.Scanner;

public class Student {
	private String firstName;
	private String lastName; 
	private String gradeSemester; 
	//private Date dateOfBirth;
	private String course;
	private int tutionBalance;
	private static int costOfSemesterFee = 319;
	private String matriculationNo;
	private static long id= 3056000;
	
	
	
	
	//Constructor  prompt user to enter Student's name, Birthday and year
	public Student() throws ParseException{
		Scanner in = new Scanner(System.in);
		System.out.println("Enter student's first name: ");
		this.firstName= in.nextLine();
		System.out.println("Enter student's last name: ");
		this.lastName= in.nextLine();
		
		/*
		 * System.out.println("Enter date of birth[DD.MM.YYYY]: "); this.dateOfBirth =
		 * this.setDateOfBirth();
		 */
		System.out.println("Enter grade semester: ");
		this.gradeSemester= in.nextLine();		
		setMatriculationNo();
		System.out.println("First name: "+this.firstName+"\nLast name:  "+this.lastName+"\nGrade semester: "+this.gradeSemester+"\nMatriculation No: "+this.matriculationNo);
		
		
	}
	
	//set DateOfBirth 
	/*
	 * public Date setDateOfBirth() throws ParseException {
	 * 
	 * String dateFormat= "DD.MM.YYYY"; Scanner scanner = new Scanner(System.in);
	 * Date dob = new SimpleDateFormat(dateFormat).parse(scanner.nextLine());
	 * 
	 * return dob;
	 * 
	 * }
	 */
	
	
	// Generate Matriculation Number
	private void setMatriculationNo() {
		id++;
		this.matriculationNo = gradeSemester + "-" + id; 
	}
	
	//Enroll in Courses 
	
	
	//View balance
	
	//Pay Tutution
	
	//Show Status

}
