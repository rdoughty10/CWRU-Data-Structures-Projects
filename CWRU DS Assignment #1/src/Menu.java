//This class contains my demonstration of the implementation of Course.java and CourseList.java
//It uses a repeating menu that walks the user through all of the different options

import java.util.Scanner;

public class Menu {

	public Menu(){
		runMenu();
	}
	
	private void runMenu(){
		Scanner scanner = new Scanner(System.in);
		CourseList courseList = new CourseList();
	
		System.out.println("Initial Courses"); //print out the initial courses to show the user what they are dealing with
		courseList.printCourses();
		
		
		//simple introduction so that people understand 
		System.out.println("\n\nWelcome to the course menu! This program allows you to go through many functions, including");
		System.out.println("printing your courses, adding and removing courses, changing the capacity, searching, and getting");
		System.out.println("the index of a certain course. Follow directions as they are presented to you!");
		
		//I decided to use a repeating menu so that all the functions are easily accessibly to the user
		//This allows for the User to have full control over what they do with the list
		int choice;
		do {
			System.out.println("\n-----------------------------------------------");
			System.out.println("Option 1: Print List of Courses");
			System.out.println("Option 2: Add Course");
			System.out.println("Option 3: Remove Course");
			System.out.println("Option 4: Change Capacity of Course");
			System.out.println("Option 5: Get Course at Index of List");
			System.out.println("Option 6: Search for Course by ID");
			System.out.println("Option 7: Search for Course by Name");
			System.out.println("Option 8: Quit");
			
			System.out.println("What option would you like (please enter an integer between 1 and 8)");

			//this section is simply bounds checking for the input to the menu
			while(!scanner.hasNextInt()) { //this forces the user to input an integer in the menu, bounds checking
				scanner.nextLine();
			    System.out.println("That is not a valid int, please try again with an integer between 1 and 8");
			}
			choice = scanner.nextInt();
			if (choice > 8 || choice < 1) { //this gives an error if the number is out of bounds
				System.out.println("Integer is outside of range of menu, returning you to main menu.");
			}
			
			
			
			
			//switch statement allows options to be much more organized then long if else statements
			switch(choice) {
				case 1: //simply printing the courses using the helper method I created earlier
					System.out.println("\nOPERATION: Print courses.");
					courseList.printCourses();
					break;
					
					
					
				case 2: //adding a course --> have to create a course by getting a ID, name, and capacity from the User and creating a course object, then adding that using the addCourse method
					System.out.println("\nTo add a course, you must enter the course ID, course name, and capacity");
					scanner.nextLine();
					
					//getting the courseID
					System.out.println("What is the courseID (ex. CSDS233)? Please enter the ID below:");
					String courseID = scanner.nextLine();
					
					//getting the courseName
					System.out.println("What is the course name (ex. Data Structures). Please enter the course name below:");
					String courseName = scanner.nextLine();
					
					//getting the course capacity --> use a while loop to force the user to enter an integer value
					System.out.println("What is the course capacity (ex. 75). Please enter a positive integer value below: ");
					while(!scanner.hasNextInt()) { //this forces the user to input an integer in the menu, bounds checking
						scanner.nextLine();
					    System.out.println("Please enter a positive integer value:");
					}
					int capacity = scanner.nextInt();
					
					//create the course object using the inputted ID, name and capacity
					Course course = new Course(courseID, courseName, capacity);
					
					//getting the index --> use a while loop to force the user to enter an integer value like above (do not bounds check for negative numbers but use a try-except below to cover for that
					System.out.println("What index of the list would you like to put the course at? Please enter an integer between 0-9");
					while(!scanner.hasNextInt()) {
						scanner.next();
						System.out.println("Please enter an integer value between 0-9:");
					}
					int index = scanner.nextInt();
					
					
					//try and except statement so that if the user adds a negative number, the program will not crash --> I find this much simpler than bounds checking the integer before
					System.out.println("\n----- Adding Course -----");
					courseList.addCourse(index, course);
//					try{
//						courseList.addCourse(index, course);
//						System.out.println("\n------ Successful -------");
//					}catch(Exception e) {
//						System.out.println("\n------ Unsuccessful -------");
//						System.out.println("Your index was out of range.");
//					};
					
					
					break;
				
					
					
					
				case 3: //removing a course --> just need the index from the user
					System.out.println("\nWhat index of the list would you like to remove? Please enter an integer between 0-9");
					scanner.nextLine();
					while(!scanner.hasNextInt()) {
						scanner.next();
						System.out.println("Please enter an integer value between 0-9:");
					}
					int removeIndex  = scanner.nextInt();
					
					//use a try-except to make sure that if they enter a negative value then the program will not crash
					try{
						boolean success = courseList.removeCourse(removeIndex);
						if(!success) { //this just tests if the remove method was successful and tells the user if not (aka if the index is in the range of the array but there is no class at that index)
							System.out.println("\n------ Unsuccessful -------");
							System.out.println("\nThere is no course available to remove at that index");
						}else {
							System.out.println("\n------ Successful -------");
						}
					}catch(Exception e) {
						System.out.println("\n------ Unsuccessful -------");
						System.out.println("Either your index was out of range or your course list is empty.");
					};
					break;
					
				case 4: //change capacity --> just need to get course ID and new capacity from user
					System.out.println("What course ID would you like to change the capacity for? (ex. CSDS233)");
					scanner.nextLine();
					String courseID2 = scanner.nextLine();
					System.out.println("What capacity would you like to change that to? (Please enter a positive integer value)");
					while(!scanner.hasNextInt()) {
						scanner.next();
						System.out.println("Invalid response! Please enter a positive integer value:");
					}
					int capacity2 = scanner.nextInt();
					
					
					courseList.changeCapacity(courseID2, capacity2);
					
					break;
				
				case 5: //Get course by index --> just need the index from the user
					System.out.println("What index would you like to see? (Enter a integer from 0-9) ");
					scanner.nextLine();
					while(!scanner.hasNextInt()) { //make sure they enter an integer
						scanner.next();
						System.out.println("Please enter an integer value between 0-9:");
					}
					int index2 = scanner.nextInt();
					
					System.out.println("\n----- Getting Course -----");
					try{
						courseList.getCourseWithIndex(index2);
						System.out.println("\n------ Successful -------");
					}catch(Exception e) {
						System.out.println("\n------ Unsuccessful -------");
						System.out.println("Your index was out of range.");
					};
					break;
					
				case 6: //the course ID search
					System.out.println("What course ID would you like to search for? (ex. CSDS233)");
					scanner.nextLine();
					courseID = scanner.nextLine();
					System.out.println("\nOPERATION: Search for course with ID: " + courseID);
					int courseIDIndex=courseList.searchCourseID(courseID);
					if(courseIDIndex >= 0) { //if courseIDIndex returns -1 there is not a course
						System.out.println("The course you are looking for is at index " + courseIDIndex);
					}else {
						System.out.println("This course is not in the system!");
					}
					break;
					
				case 7: //the course name search
					System.out.println("What course name would you like to search for? (ex. Data Structures)");
					scanner.nextLine();
					courseName = scanner.nextLine();
					System.out.println("\nOPERATION: Search for course with name: " + courseName);
					int courseIndex=courseList.searchCourseName(courseName);
					if(courseIndex >= 0) { //if courseIndex returns -1 there is not a course
						System.out.println("The course you are looking for is at index " + courseIndex);
					}else {
						System.out.println("This course is not in the system!");
					}
					break;
					
				
				case 8: 
					break;
					
			}
			
		}while(choice != 8);
		
		//concluding sentence after they quit the program
		System.out.println("\nThank you for taking part in this program!");
		
	}
	
	public static void main(String[] args) {
		Menu menu = new Menu();
	}

}
