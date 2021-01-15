
public class CourseList {

	
	//instance variable --> only the list of courses (private for encapsulation, instantiated in constructor) and it's size
	final private Course[] listOfCourses;
	private int size; //I used a size variable so I did not have to iterate through the list every time I wanted to know how many courses I had
	
	//CONSTRUCTOR
	public CourseList() {
		listOfCourses = new Course[10]; //instantiated to size of 10 as directed
		
		//I added 5 courses during initialization so the user doesn't start with an empty list (better for demonstration purposes)
		Course course1 = new Course("CSDS233", "Data Structures", 75);
		Course course2 = new Course("MATH223", "Multivariable Calc", 40);
		Course course3 = new Course("ENGR200", "Statics", 200);
		Course course4 = new Course("CHEM111", "Chemistry", 400);
		Course course5 = new Course("FSNA135", "BioDesign", 20);
		listOfCourses[0] = course1;
		listOfCourses[1] = course2;
		listOfCourses[2] = course3;
		listOfCourses[3] = course4;
		listOfCourses[4] = course5;
		
		size = size(); //must call size method to set size at initial startup
	}
	
	//CLASS METHODS
	public int size() { 
		int i = 0;
		while(!(listOfCourses[i] == null)) {
			i++;
		}
		size = i;
		return size;
	}
	
	public void addCourse(int i, Course course) {
		if (i < 0) {
			throw new IllegalArgumentException("Index out of range");
		}
		
		System.out.println("\nOPERATION: Add a course to index " + i + ".");
		System.out.println("COURSE: courseID: " + course.getCourseID() + ", courseName: " + course.getCourseName() + ", capacity: " + course.getCapacity());
		
		System.out.println("\nList before the operation:");
		printCourses();
		
		
		//Adding the course
		if((i+1)>size) { //test if the i+1 is greater than the size and if so, add it to the end (size index)
			listOfCourses[size] = course;
		}else { //if not, loop through from the last course to the index and move all the courses back one, then add the new course
			for(int j = size ; j>i; j--) {
				listOfCourses[j] = listOfCourses[j-1];
			}
			listOfCourses[i] = course;
		}
		
		size(); //must update the size variable after 
		System.out.println("\nList after the operation:");
		printCourses();
		
	}
	
	public boolean removeCourse(int i) {
		System.out.println("\nOPERATION: Remove the course at index " + i + ".");
		if(i >= size || i < 0) { //making sure the index is in removable range (aka the size)
			return false;
		}else {
			
			System.out.println("\nList before the operation:");
			printCourses();
			
			//removing the course
			for(int j = i; j<size; j++) {
				listOfCourses[j] = listOfCourses[j+1];
			}
			
			size();
			System.out.println("\nList after the operation:");
			printCourses();
			
			return true;
		}
	}
	

	public boolean changeCapacity(String courseID, int capacity) {
		
		int index = -1;
		for(int i = 0; i < size; i++) {
			if(listOfCourses[i].getCourseID().equals(courseID)) {
				listOfCourses[i].setCapacity(capacity);
				index = i;
			}
		}
		System.out.println("\nOPERATION: Change the capacity of course(s) with Course ID:" + courseID + " to " + capacity + ".");
		
		if (index >= 0) {
			
			System.out.println("\nList before the operation:");
			printCourses();
			
			listOfCourses[index].setCapacity(capacity);
			
			System.out.println("\nList after the operation:");
			printCourses();
			
			return true;
		}else {
			System.out.println("There is no course with that course ID."); //if the search returns -1, then this error message will come through
		}
		return false;
	}
	
	public Course getCourseWithIndex(int i) {
		System.out.println("\nOPERATION: Look at the course at index " + i + ".");
		if(i >= size || i < 0) {
			System.out.println("There is no course at that index!");
		}else {
			System.out.println(i + ". courseID: " + listOfCourses[i].getCourseID() + ", courseName: " + listOfCourses[i].getCourseName() + ", capacity: " + listOfCourses[i].getCapacity());
			return listOfCourses[i];
		}
		return null;
		
	}
	
	public int searchCourseID(String courseID) {
		//simple loop through from 0-size
		for(int i = 0; i < size; i++) {
			if(listOfCourses[i].getCourseID().equals(courseID)) {
				return i;
			}
		}
		return -1;
		
	}
	
	public int searchCourseName(String courseName) {
		//simple loop through from 0-size
		for(int i = 0; i < size; i++) {
			if(listOfCourses[i].getCourseName().equals(courseName)) {
				return i;
			}
		}
		return -1;
		
	}
	
	//helper method that I can call to show results --> not needed but I use it a lot and it's much better than writing it every time
	public void printCourses() {
		if(size() == 0) {
			System.out.println("Error: You have no courses");
		}else {
			for(int i = 0; i<size; i++) {
				System.out.println(i + ". courseID: " + listOfCourses[i].getCourseID() + ", courseName: " + listOfCourses[i].getCourseName() + ", capacity: " + listOfCourses[i].getCapacity());
			}
		}
		
	}
	
	
}
